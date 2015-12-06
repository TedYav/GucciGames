package voogasalad_GucciGames.gameData.wrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

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


	public GameEngine(String initialLevel){
	    myLevelsMap = new HashMap<String,GameLevelEngine>();
	    this.myInitialLevel = initialLevel;
	    this.myCurrentLevel = initialLevel;
	    
	    myGameName = "RandomName";
	    
	}
	
	public void resetGame(){
		myCurrentLevel = myInitialLevel;
	}
	
	@Override
	public void changeCurrentLevel(String newGameLevel){
		myCurrentLevel = newGameLevel;
	}
	
	
	public GameEngine(String initialLevel, String gameName){
		this(initialLevel);
		myGameName = gameName;
	}
	@Override
	public String getGameName() {
		// TODO Auto-generated method stub
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
		
//		return GameLevelEngine;
	}


	
	/*
	public void swapLevels(int first, int second){
		if (myLevelsMap.containsKey(first) && myLevelsMap.containsKey(second)){
			GameLevelEngine gameOne = myLevelsMap.get(first);
			gameOne.changeID(second);
			
			GameLevelEngine gameTwo = myLevelsMap.get(second);
			gameTwo.changeID(first);
			
			myLevelsMap.put(first, gameTwo);
			myLevelsMap.put(second, gameOne);
		}
		else{
			System.out.println("One or more level(s) not found");
		}
	}
    */

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



}

