package voogasalad_GucciGames.gameData.wrapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

/**
 * 
 * This class will be a wrapper for the game engine
 * and the information to configure the game player
 * gui
 *
 */


public class GameInfo implements IGameInfoToGAE{
	private MainGameEngine myEngine;
	// these will hold the components which go in each part of the player gui
	// format can be changed
	private List<String> myRightComponents;	// hold class names
	private List<String> myLeftComponents;
	private List<String> myBottomComponents;
	private Map<Integer,GameLevel> myLevelsMap;
	private static int myLevelID = 0;
	private String myGameName;
	
	public GameInfo(List<String> leftComponents, List<String> rightComponents, List<String> bottomComponents){
//	    myEngine=engine;
	    myLevelsMap = new TreeMap<Integer,GameLevel>();
	    myGameName = "Game " + Math.round((Math.random()*10000));
	    myRightComponents=rightComponents;
	    myLeftComponents=leftComponents;
	    myBottomComponents=bottomComponents;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.myGameName;
	}
	
	/**
	 * Default level adder
	 * Does not allow you to choose what next level is
	 * (nextLevelID == LevelID+1)
	 * @return
	 */	
	@Override
	public void addLevel(String gameName, boolean chooseable, MainGameEngine engine){
		//At the moment, automatically sets next level as the next int in the map
		myEngine = engine;		
		int nextID = myLevelID + 1;
		GameLevel gameLevel = new GameLevel(myLevelID, nextID, gameName, chooseable, engine);
		myLevelsMap.put(myLevelID, gameLevel);
		myLevelID++;
		
//		return gameLevel;
	}	
	
	/**
	 * Allows you to choose where this level will lead to
	 * end game level ID = -1
	 * @param gameName
	 * @param chooseable
	 * @param engine
	 * @param nextLevelId
	 * @return
	 */	
	@Override
	public void addLevel(String gameName, boolean chooseable, int nextLevelId, MainGameEngine engine){
		GameLevel gameLevel = new GameLevel(myLevelID, nextLevelId, gameName, chooseable, engine);		
		myEngine = engine;
		myLevelsMap.put(myLevelID, gameLevel);
		myLevelID++;
		
//		return gameLevel;
	}

	@Override
	public void deleteLevel(String gameName) {
		int key = getLevelID(gameName);
		if (key > -1){
			for (int i = key; i<myLevelsMap.keySet().size()-1; i++){
				myLevelsMap.put(i, myLevelsMap.get(i+1));
			}
			myLevelsMap.remove(myLevelsMap.keySet().size()-1);
		}

	}
	
	@Override
	public int getLevelID(String name){		
		for (Integer key: myLevelsMap.keySet()){
			if (myLevelsMap.get(key).getLevelName().equals(name)){
				return key;
			}
		}
		return -1;
	}
	
	@Override
	public void swapLevelIDs(String one, String two){
		int first = getLevelID(one);
		int second = getLevelID(two);
		if (myLevelsMap.containsKey(first) && myLevelsMap.containsKey(second)){
			GameLevel gameOne = myLevelsMap.get(first);
			gameOne.changeID(second);
			
			GameLevel gameTwo = myLevelsMap.get(second);
			gameTwo.changeID(first);
			
			myLevelsMap.put(first, gameTwo);
			myLevelsMap.put(second, gameOne);
		}
	}
	
	@Override
	public Map<Integer, String> getLevelsMap() {
		Map<Integer,String> temp = new TreeMap<Integer,String>();
		for (Integer key: myLevelsMap.keySet()){
			temp.put(key, myLevelsMap.get(key).getLevelName());
		}
		return temp;
	}
	
	public GameEngineToGamePlayerInterface getEngine() {
	    return myEngine;
	}
	public List<String> getLeftComponents() {
	    return myLeftComponents;
	}
	public List<String> getRightComponents() {
	    return myRightComponents;
	}

    public List<String> getBottomComponents () {
        return myBottomComponents;
    }








}

