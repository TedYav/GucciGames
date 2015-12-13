package voogasalad_GucciGames.gameData.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import voogasalad_GucciGames.gameEngine.GameEngineClient;
import voogasalad_GucciGames.gameEngine.GameEnginePlayer;
import voogasalad_GucciGames.gameEngine.GameEngineServer;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.NetworkException;
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
	private Map<String, GameLevelEngine> myLevelsMap;

	private String myGameName;
	private String myCurrentLevel;
	private GameStats myGameStats;
	private List<String> played;

	private String myInitialLevel;
	private boolean isChangingLevel;
	private List<String> transferablePlayerCharacteristics;
	private boolean isEndTurn;
	

	private transient volatile GameEnginePlayer iAmAPlayer;
	private transient volatile Thread t;

	@XStreamOmitField
	private transient GameControllerEngineInterface myController;
	
	private Map<String,MapObject> myBuild;
	private Set<Integer> playerIDs;

	public GameEngine(String initialLevel) {
		myLevelsMap = new HashMap<String, GameLevelEngine>();
		this.myInitialLevel = initialLevel;
		this.myCurrentLevel = initialLevel;
		this.myGameStats = new GameStats();

		myGameName = "RandomName";

		this.transferablePlayerCharacteristics = new ArrayList<String>();
		this.transferablePlayerCharacteristics.add("PlayerScore");
		isChangingLevel = false;
		isEndTurn = false;
		this.played = new ArrayList<>();

	}

	public void setCurrentLevel(String level) {
		myCurrentLevel = level;
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
	//	iAmAPlayer = new GameEngineClient(this, ipAddr);
		t = new Thread(iAmAPlayer);
		t.start();

	}

	public void resetGame() {		
		myCurrentLevel = myInitialLevel;
	}

	@Override
	public void changeCurrentLevel(String newGameLevel) {

		if (this == null) {
			return;
		}

//		System.out.println(levelComplete);
		myCurrentLevel = newGameLevel;
//		else{
//			System.out.println("JK level has NOT changed");
//		}

		
		if (iAmAPlayer != null) {
			//iAmAPlayer.setLevelEngine(getCurrentLevel());
		}

		isChangingLevel = true;
		
		//if string returned is empty, assume game "won"
		if (myCurrentLevel == ""){
			getCurrentLevel().setEndLevel(true);
		}
		// Have to have same number of players between levels
//		myCurrentLevel = newGameLevel;

		setUpGameStats();

	}

	private boolean setUpGameStats() {
		this.getCurrentLevel().setGameStats(myGameStats);
		AllPlayers players = this.getCurrentLevel().getPlayers();
		for (Integer id : players.getAllExistingIds()) {
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
		myLevelsMap.put(levelName, myEngine);

	}

	// I'm sorry for the code below
	// Java wouldn't let me modify the return type in the interface, don't know
	// why
	// :(

	@Override
	public Map<String, IGameLevelToGamePlayer> getLevelsMap() {
		return Collections.unmodifiableMap(myLevelsMap);
	}

	@Override
	public List<String> getChoosableLevels() {
		List<String> levelNames = new ArrayList<String>();
		for (GameLevelEngine engine : myLevelsMap.values()) {
			if (engine.isMyChoosability()) {
				levelNames.add(engine.getLevelName());
			}
		}
		return levelNames;
	}

	@Override
	public void setLevel(String gameName, GameLevelEngine engine) {
		engine.setLevelName(gameName);
		myLevelsMap.put(gameName, engine);

	}

	public GameLevelEngine getCurrentLevel() {
		// TODO Auto-generated method stub
		if (isChangingLevel) {
			updateTransfer();
			isChangingLevel = false;
		}
		return myLevelsMap.get(myCurrentLevel);
	}

	private boolean updateTransfer() {
		AllPlayers players = this.myLevelsMap.get(myCurrentLevel).getPlayers();
		for (Integer id : players.getAllExistingIds()) {
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
			//iAmAPlayer.endTurn();
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
		return this.myLevelsMap.get(myCurrentLevel).getPlayers().getPlayerById(id).getCharacteristics(name);
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
		myLevelsMap.put(gameName, engine);
	}
	
	public void addToBuild(String name, MapObject mo){
		this.myBuild.put(name, mo);
	}
	
	public Map<String,MapObject> getBuild(){
		return this.myBuild;
	}

	public void updateChat(int playerID2, String string) {
		myController.updateChat("Player" + playerID2 + ": " + string);
	}
	
	public void sendMessage(String string){
	    if (iAmAPlayer==null) {
	        myController.updateChat("Player" + getCurrentLevel().getGameParameters().whoseTurn() + ": " + string);
	    }
	    else {
		//iAmAPlayer.sendMessage(string);
	    }
	    if (iAmAPlayer.getClass().getSimpleName().equals(GameEngineServer.class.getSimpleName())) {
	        myController.updateChat("Player" + getCurrentLevel().getGameParameters().whoseTurn() + ": " + string);
	    }
	}

	public void addMapObjects(Map<String, MapObject> allMapObjects) {
		for (String s : myLevelsMap.keySet()) {
			myLevelsMap.get(s).addMapObjectsForLevels(allMapObjects);
		}
		
	}

	public void notifyEngine(NetworkException networkException) {
		// TODO Auto-generated method stub
		
	}

	public Set<Integer> getPlayerIDs() {
		return playerIDs;
	}

}
