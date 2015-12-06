package voogasalad_GucciGames.gameData.wrapper;

import java.lang.reflect.Constructor;
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
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameController;
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

	private String myInitialLevel;

	private volatile GameEnginePlayer iAmAPlayer;
	private volatile Thread t;
	
	@XStreamOmitField
	private GameController myController; 
	
	public GameEngine(String initialLevel){
	    myLevelsMap = new HashMap<String,GameLevelEngine>();
	    this.myInitialLevel = initialLevel;
	    this.myCurrentLevel = initialLevel;

	    myGameName = "RandomName";
	}
	
	public void setCurrentLevel(String level){
		myCurrentLevel = level;
	}
	
	public GameEngine(String initialLevel, String gameName){
		this(initialLevel);
		myGameName = gameName;
	}
	
	public void beHost(){
		iAmAPlayer = new GameEngineServer(this);
		t = new Thread(iAmAPlayer);
		t.start();
	}
	
	public void beClient(String ipAddr){
		iAmAPlayer = new GameEngineClient(this, ipAddr);
		t = new Thread(iAmAPlayer);
		t.start();

	}

	public void resetGame(){
		myCurrentLevel = myInitialLevel;
	}

	@Override
	public void changeCurrentLevel(String newGameLevel){
		
		myCurrentLevel = newGameLevel;

		
		if(iAmAPlayer != null){
		iAmAPlayer.setLevelEngine(getCurrentLevel());
		}
	}

	@Override
	public String getGameName() {
		return this.myGameName;
	}

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
	public void setLevel(String gameName, GameLevelEngine engine) {
		engine.setName(gameName);
		myLevelsMap.put(gameName, engine);

	}

	public GameLevelEngine getCurrentLevel() {
		// TODO Auto-generated method stub
		return myLevelsMap.get(myCurrentLevel);
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
		if(iAmAPlayer != null){
		iAmAPlayer.endTurn();
		}
		return getCurrentLevel().endTurn();
	}

	@Override
	public int getTurnPlayerID() {
		
		return getCurrentLevel().getTurnPlayerID();
		
		
	}

	@Override
	public GridCoordinateParameters getPossibleCoordinates(String action,
			PlayerMapObjectInterface mapObject) {
		return null;
	}

	@Override
	public ChangedParameters performAction(String action,
			PlayerMapObjectInterface mapObject, ATargetCoordinate target) {
		return null;
	}

	@Override
	public int getMapWidth() {
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
    public boolean hasLevelEnded () {
        return getCurrentLevel().hasLevelEnded();
    }

	public void refreshGUI() {
		// TODO Auto-generated method stub
		myController.refreshGUI();
	}

	public GameController getController() {
		return myController;
	}

	@Override
	public void setController(GameController myController) {
		this.myController = myController;
	}



}

