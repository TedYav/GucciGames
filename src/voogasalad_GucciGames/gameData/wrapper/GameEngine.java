package voogasalad_GucciGames.gameData.wrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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


public class GameEngine extends AGameInfo implements IGameInfoToGAE, GameInfoToGamePlayer {
	private static final int MAINMENU = -1;

	
	private Map<Integer,GameLevel> myLevelsMap;
	private int myLevelID = 0;
	private String myGameName;
	private List<GameLevel> myChoosableLevelsList;
	private GuiData guiData;

	public GameEngine(String gameName){
	    myLevelsMap = new TreeMap<Integer,GameLevel>();
	    myGameName = gameName;
	    guiData=new GuiData();
	}
	
	public GameEngine(){
	    
	    myLevelsMap = new TreeMap<Integer,GameLevel>();
	    myGameName = "Game " + Math.round((Math.random()*10000));
	       guiData=new GuiData();

	}
	
	@Override
	public String getGameName() {
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
	public void addLevel(String gameName, boolean chooseable){
		//At the moment, automatically sets next level as the next int in the map	
		int nextID = myLevelID + 1;
		GameLevel gameLevel = new GameLevel(myLevelID, nextID, gameName, chooseable);
		if (chooseable){
			myChoosableLevelsList.add(gameLevel);
		}
		myLevelsMap.put(myLevelID, gameLevel);
		myLevelID++;
		
//		return gameLevel;
	}	
	
	/**
	 * Adds a new level and returns a reference to it.
	 * @param gameName
	 * @return
	 */	
	@Override
	public GameLevel addLevel(String levelName){
		GameLevel gameLevel = new GameLevel(myLevelID, MAINMENU, levelName, true);		
		myLevelsMap.put(myLevelID, gameLevel);
		myLevelID++;
		
		return myLevelsMap.get(myLevelID - 1);
//		return gameLevel;
	}

	@Override
	public void deleteLevel(int id) {
		if (id > -1){
			myLevelsMap.remove(id);
			for (int i = id; i<myLevelsMap.keySet().size()-1; i++){
				myLevelsMap.put(i, myLevelsMap.remove(i+1));
			}
		}
		else{
			System.out.println("Level not found");
		}

	}
	

	public int getLevelID(String name){		
		for (Integer key: myLevelsMap.keySet()){
			if (myLevelsMap.get(key).getLevelName().equals(name)){
				return key;
			}
		}
		System.out.println("Level not found");
		return -1;
	}
	
	public void swapLevels(int first, int second){
		if (myLevelsMap.containsKey(first) && myLevelsMap.containsKey(second)){
			GameLevel gameOne = myLevelsMap.get(first);
			gameOne.changeID(second);
			
			GameLevel gameTwo = myLevelsMap.get(second);
			gameTwo.changeID(first);
			
			myLevelsMap.put(first, gameTwo);
			myLevelsMap.put(second, gameOne);
		}
		else{
			System.out.println("One or more level(s) not found");
		}
	}
    

	// I'm sorry for the code below
	// Java wouldn't let me modify the return type in the interface, don't know why
	// :(
	@Override
	public Map<Integer, GameLevel> getLevelsMap() {
		return Collections.unmodifiableMap(myLevelsMap);
	}
	
	@Override
	public Map<Integer, IGameLevelToGamePlayer> getLevels() {
		return Collections.unmodifiableMap(myLevelsMap);
	}
	
	@Override
	public List<String> getChoosableLevels() {
		List<String> levelNames = new ArrayList<String>();
		for (int i=0; i<myChoosableLevelsList.size(); i++){
			levelNames.add(myChoosableLevelsList.get(i).getLevelName());
		}
		return levelNames;
	}

	public List<String> getGuiComponents(String location) {
	    System.out.println(location);
	    return guiData.getComponents(location);
	}
	public void setGuiComponents(String location, List<String> components) {
	    guiData.setComponents(location, components);
	}

	@Override
	public void setEngine(String gameName, MainGameEngine engine) {
		for (Integer key: myLevelsMap.keySet()){
			if(gameName.equals(myLevelsMap.get(key).getLevelName())){
				myLevelsMap.get(key).setGameEngine(engine);
			}
		}
		
	}

    public GuiData getGuiData () {
        return guiData;
    }

    public void setGuiData (GuiData guiData) {
        this.guiData = guiData;
    }


}

