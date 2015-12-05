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


public class GameInfo implements IGameInfoToGAE{
	private static final int MAINMENU = -1;
	// these will hold the components which go in each part of the player gui
	// format can be changed
	private List<String> myRightComponents;	// hold class names
	private List<String> myLeftComponents;
	private List<String> myBottomComponents;
	
	private Map<Integer,GameLevel> myLevelsMap;
	private int myLevelID = 0;
	private String myGameName;
	
	public GameInfo(String gameName){
		this(gameName, defaultLeft(), defaultRight(), defaultBottom());
	}
	
	// TODO: CHANGE THIS -> JOHN DAI
	
	private static List<String> defaultBottom() {
	
     List<String> bottomComponents=new ArrayList<String>();
         bottomComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.MainMenuButton");
        
         // REMOVED UNTIL WE MAKE DISPLAYCOMPONENT AND WINDOWCOMPONENT INTERCHANGEABLE
         //bottomComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini.MiniMap");
         return bottomComponents;
	}

	private static List<String> defaultRight() {
        List<String> rightComponents=new ArrayList<String>();
		 rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.ActionDisplay");
	     rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.BuildUnitsDisplay");
	     rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.EndTurnButton");
	     rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.GameStatsDisplay");
	     return rightComponents;
	}

	private static List<String> defaultLeft() {
		 List<String> leftComponents=new ArrayList<String>();
	     leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectImage");
	     leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectDetails");
	     leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayChat");
	     leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini.MiniMap");
	     return leftComponents;
	}

	public GameInfo(String gameName, List<String> leftComponents, List<String> rightComponents, List<String> bottomComponents){
	    myLevelsMap = new TreeMap<Integer,GameLevel>();
	    myGameName = gameName;
	    myRightComponents=rightComponents;
	    myLeftComponents=leftComponents;
	    myBottomComponents=bottomComponents;
	}
	
	@Override
	public String getGameName() {
		// TODO Auto-generated method stub
		return this.myGameName;
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

	}
	
	@Override
	public void swapLevels(int first, int second){
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
	public Map<Integer, GameLevel> getLevelsMap() {
		return Collections.unmodifiableMap(myLevelsMap);
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

