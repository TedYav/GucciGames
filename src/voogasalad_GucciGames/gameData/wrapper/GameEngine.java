package voogasalad_GucciGames.gameData.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import voogasalad_GucciGames.gameEngine.GameEngineClient;
import voogasalad_GucciGames.gameEngine.GameEnginePlayer;
import voogasalad_GucciGames.gameEngine.GameEngineServer;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameController;
import voogasalad_GucciGames.gameplayer.controller.GameControllerEngineInterface;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;

/**
 *
 * This class will be a wrapper for the game engine and the information to
 * configure the game player gui
 *
 */


public class GameEngine implements IGameInfoToGAE, GameEngineToGamePlayerInterface {
	private static final int MAINMENU = -1;
	private String myGameName;
	private GameStats myGameStats;
	private List<String> played;

	private String myInitialLevel;
	private boolean isChangingLevel;
	private List<String> transferablePlayerCharacteristics;

	private transient volatile GameEnginePlayer iAmAPlayer;
	private transient volatile Thread t;

	@XStreamOmitField
	private transient GameControllerEngineInterface myController;
	
	private Map<String,MapObject> myBuild;

	public GameEngine(String initialLevel) {
		
		this.myInitialLevel = initialLevel;
		this.myGameStats = new GameStats();
		myGameStats.setMyCurrentLevel(initialLevel);
		
		//default
		myGameStats.setLevelChooser(new DefaultLevelChooser());
		
		myGameName = "RandomName";

		this.transferablePlayerCharacteristics = new ArrayList<String>();
		this.transferablePlayerCharacteristics.add("PlayerScore");
		isChangingLevel = false;
		this.played = new ArrayList<>();

	}
	
	public void setLevelChooser(ILevelChooser c){
		myGameStats.setLevelChooser(c);
	}

	public void setCurrentLevel(String level) {
		myGameStats.setMyCurrentLevel(level);
	}

	public GameEngine(String initialLevel, String gameName) {
		this(initialLevel);
		myGameName = gameName;
	}
	
	public void beHost() {
		iAmAPlayer = new GameEngineServer(this);
		t = new Thread(iAmAPlayer);
		t.start();
	}

	public void beClient(String ipAddr) {
		iAmAPlayer = new GameEngineClient(this, ipAddr);
		t = new Thread(iAmAPlayer);
		t.start();

	}

	public void resetGame() {		
		myGameStats.setMyCurrentLevel(myInitialLevel);
	}

	
	//this is how you change levels, how do you know what to change to? that's when you should call IlevelChooser.nextLevel();
	//creates a string that's passed into here.
	@Override
	public void changeCurrentLevel(String newGameLevel) {
		//where did this come from?
		if (this == null) {
			return;
		}

//		System.out.println(levelComplete);
		myGameStats.setMyCurrentLevel(newGameLevel);
//		else{
//			System.out.println("JK level has NOT changed");
//		}

		
		if (iAmAPlayer != null) {
			iAmAPlayer.setLevelEngine(getCurrentLevel());
		}

		isChangingLevel = true;
		
		//if string returned is empty, assume game "won"

		// Have to have same number of players between levels
//		myCurrentLevel = newGameLevel;

		setUpGameStats();

	}

	private boolean setUpGameStats() {
		this.getCurrentLevel().setGameStats(myGameStats);
		AllPlayers players = this.getCurrentLevel().getPlayers();
		for (Integer id : players.getAllIds()) {
			GamePlayerPerson player = players.getPlayerById(id);
			for (String ch : transferablePlayerCharacteristics) {
				if (player.hasCharacteristic(ch)) {
					this.myGameStats.addTransferableCharacteristic(id, player.getCharacteristics(ch), ch);
				}
			}
		}
		return true;
	}

	

	@Override
	public String getGameName() {
		return this.myGameName;
	}


	/**
	 * Adds a new level and returns a reference to it.
	 * 
	 * @param gameName======
	 * @return
	 */
	public void addLevel(String levelName, GameLevelEngine myEngine) {
		myEngine.setLevelName(levelName);
		myGameStats.getLevelsMap().put(levelName, myEngine);

	}

	// I'm sorry for the code below
	// Java wouldn't let me modify the return type in the interface, don't know
	// why
	// :(

	@Override
	public Map<String, IGameLevelToGamePlayer> getLevelsMap() {
		return Collections.unmodifiableMap(myGameStats.getLevelsMap());
	}

	@Override
	public List<String> getChoosableLevels() {
		List<String> levelNames = new ArrayList<String>();
		for (GameLevelEngine engine : myGameStats.getLevelsMap().values()) {
			if (engine.isMyChoosability()) {
				levelNames.add(engine.getLevelName());
			}
		}
		return levelNames;
	}

	@Override
	public void setLevel(String gameName, GameLevelEngine engine) {
		engine.setLevelName(gameName);
		myGameStats.getLevelsMap().put(gameName, engine);
	}

	public GameLevelEngine getCurrentLevel() {
		// TODO Auto-generated method stub
		if (isChangingLevel) {
			updateTransfer();
			isChangingLevel = false;
		}
		return myGameStats.getLevelsMap().get(myGameStats.getMyCurrentLevel());
	}

	private boolean updateTransfer() {
		AllPlayers players = this.myGameStats.getLevelsMap().get(myGameStats.getMyCurrentLevel()).getPlayers();
		for (Integer id : players.getAllIds()) {
			if (this.myGameStats.contains(id)) {
				GamePlayerPerson player = players.getPlayerById(id);
				Map<String, APlayerChars> map = this.myGameStats.getCharacteristics(id);
				for (String ch : map.keySet()) {
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
		
		
		GameParametersInterface myParams = getCurrentLevel().endTurn();
		
		if (iAmAPlayer != null) {
			iAmAPlayer.endTurn();
		}
		return myParams;
	}

	@Override
	public int getTurnPlayerID() {

		return getCurrentLevel().getTurnPlayerID();

	}

	@Override
	public GridCoordinateParameters getPossibleCoordinates(String action, PlayerMapObjectInterface mapObject) {
		return null;
	}

	@Override
	public ChangedParameters performAction(String action, PlayerMapObjectInterface mapObject,
			ATargetCoordinate target) {
		return null;
	}

	@Override
	public int getMapWidth() {
		// TODO Auto-generated method stub
		return getCurrentLevel().getMapWidth();
	}

	@Override
	public int getMapHeight() {
		return getCurrentLevel().getMapHeight();
	}

	@Override
	public GameParametersInterface getGameParameters() {
		return getCurrentLevel().getGameParameters();
	}

	@Override
	public boolean hasLevelEnded() {
		return getCurrentLevel().hasLevelEnded();
	}
	
//	@Override
//	public void levelStart(){
//		System.out.println("levelStart "+levelComplete);
//		levelComplete = true;
//	}
	
//	public void setLevelStart(){
//		getCurrentLevel().setStartLevel();
//	}


	@Override
	public APlayerChars getPlayerCharacteristic(String name, int id) {
		// TODO Auto-generated method stub
		return this.myGameStats.getLevelsMap().get(myGameStats.getMyCurrentLevel()).getPlayers().getPlayerById(id).getCharacteristics(name);
	}

	@Override
	public void addTransferableCharacteristic(String name) {
		this.transferablePlayerCharacteristics.add(name);

	}

	public void refreshGUI() {
		myController.refreshGUI();
	}

	public GameControllerEngineInterface getController() {
		return myController;
	}

	@Override
	public void setController(GameControllerEngineInterface myController) {
		this.myController = myController;
	}

	@Override
	public void setEngine(String gameName, GameLevelEngine engine) {
		myGameStats.getLevelsMap().put(gameName, engine);
	}
	
	public void addToBuild(String name, MapObject mo){
		this.myBuild.put(name, mo);
	}
	
	public Map<String,MapObject> getBuild(){
		return this.myBuild;
	}
	
	@Override
	public boolean gameOver(){
		if(myGameStats.nextLevel().equals("")){
			return true;
		}
		return false;
	}

}
