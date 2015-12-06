package voogasalad_GucciGames.gameData.wrapper;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerScore;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;

/**
 *
 * This class will be a wrapper for the game engine
 * and the information to configure the game player
 * gui
 *
 */


public class GameEngine implements IGameInfoToGAE, GameEngineToGamePlayerInterface {
	private static final int MAINMENU = -1;
	private Map<String,GameLevelEngine> myLevelsMap;
	private String myGameName;
	private String myCurrentLevel;
	private GameStats myGameStats;
	private List<String> played;

	private String myInitialLevel;
	
	private boolean isChangingLevel;
	private List<String> transferablePlayerCharacteristics;


	public GameEngine(String initialLevel){
		myLevelsMap = new HashMap<String,GameLevelEngine>();
		this.myInitialLevel = initialLevel;
		this.myCurrentLevel = initialLevel;
		this.myGameStats = new GameStats();

		myGameName = "RandomName";

		this.transferablePlayerCharacteristics = new ArrayList<String>();
		this.transferablePlayerCharacteristics.add("PlayerScore");
		isChangingLevel = false;
		this.played = new ArrayList<>();

	}

	public void resetGame(){
		//then here too
		myCurrentLevel = myInitialLevel;
	}

	@Override
	public void changeCurrentLevel(String newGameLevel){
		if(this==null){
			return;
		}
		isChangingLevel = true;
		//Have to have same number of players between levels
		myCurrentLevel = newGameLevel;
//		System.out.println("Tina " + newGameLevel);
//		if (!played.contains(newGameLevel)){
			setUpGameStats();
//			System.out.println("here "+newGameLevel);
//			played.add(newGameLevel);
//		}
	}

	private boolean setUpGameStats(){
		this.getCurrentLevel().setGameStats(myGameStats);
		AllPlayers players = this.getCurrentLevel().getPlayers();
		for (Integer id: players.getAllIds()){
			GamePlayerPerson player = players.getPlayerById(id);
			for(String ch: transferablePlayerCharacteristics){
				if(player.hasCharacteristic(ch)){
					this.myGameStats.addTransferableCharacteristic(id, player.getCharacteristics(ch),ch);
				}
			}
		}
		return true;
	}

	public GameEngine(String initialLevel, String gameName){
		this(initialLevel);
		myGameName = gameName;
	}
	@Override
	public String getGameName() {
		return this.myGameName;
	}



	/**
	 * Adds a new level and returns a reference to it.
	 * @param gameName======
	 * @return
	 */
	public void addLevel(String levelName, GameLevelEngine myEngine){
		myEngine.setName(levelName);
		myLevelsMap.put(levelName, myEngine);

	}

	// I'm sorry for the code below
	// Java wouldn't let me modify the return type in the interface, don't know why
	// :(
	@Override
	public Map<String, IGameLevelToGamePlayer> getLevelsMap() {
		return Collections.unmodifiableMap(myLevelsMap);
	}

	@Override
	public List<String> getChoosableLevels() {
		List<String> levelNames = new ArrayList<String>();
		for (GameLevelEngine engine : myLevelsMap.values()){
			if(engine.isMyChoosability()){
				levelNames.add(engine.getLevelName());
			}
		}
		return levelNames;
	}

	@Override
	public void setEngine(String gameName, GameLevelEngine engine) {
		myLevelsMap.put(gameName, engine);

	}

	public GameLevelEngine getCurrentLevel() {
		// TODO Auto-generated method stub
		if(isChangingLevel){
			updateTransfer();
			isChangingLevel = false;
		}
		return myLevelsMap.get(myCurrentLevel);
	}
	
	private boolean updateTransfer(){
		AllPlayers players = this.myLevelsMap.get(myCurrentLevel).getPlayers();
		for (Integer id: players.getAllIds()){
			if(this.myGameStats.contains(id)){
				GamePlayerPerson player = players.getPlayerById(id);
				Map<String,APlayerChars> map = this.myGameStats.getCharacteristics(id);
				for(String ch: map.keySet()){
					player.addCharacterstic(ch, map.get(ch));
				}
			}
		}
		return true;
	}


	@Override
	public String getName() {
		return getCurrentLevel().getLevelName();
	}

	@Override
	public List<PlayerMapObjectInterface> getInitialState() {
		return getCurrentLevel().getInitialState();
	}

	@Override
	public GameParametersInterface endTurn() {
		return getCurrentLevel().endTurn();
	}

	@Override
	public int getTurnPlayerID() {
		return getCurrentLevel().getTurnPlayerID();
	}

	@Override
	public GridCoordinateParameters getPossibleCoordinates(String action,
			PlayerMapObjectInterface mapObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChangedParameters performAction(String action,
			PlayerMapObjectInterface mapObject, ATargetCoordinate target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMapWidth() {
		// TODO Auto-generated method stub
	    System.out.println(getCurrentLevel());
		return getCurrentLevel().getMapWidth();
	}

	@Override
	public int getMapHeight() {
		// TODO Auto-generated method stub
		return getCurrentLevel().getMapHeight();
	}

	@Override
	public GameParametersInterface getGameParameters() {
		// TODO Auto-generated method stub
		return getCurrentLevel().getGameParameters();
	}
	
	@Override
	public boolean hasLevelEnded () {
		return getCurrentLevel().hasLevelEnded();
	}

	@Override
	public APlayerChars getPlayerCharacteristic(String name, int id) {
		// TODO Auto-generated method stub
		return this.myLevelsMap.get(myCurrentLevel).getPlayers().getPlayerById(id).getCharacteristics(name);
	}

	@Override
	public void addTransferableCharacteristic(String name) {
		this.transferablePlayerCharacteristics.add(name);

	}




}

