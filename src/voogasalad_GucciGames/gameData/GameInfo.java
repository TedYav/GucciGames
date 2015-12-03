package voogasalad_GucciGames.gameData;

import java.util.Collection;
import java.util.List;
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


public class GameInfo {
	private MainGameEngine myEngine;
	// these will hold the components which go in each part of the player gui
	// format can be changed
	private List<String> myRightComponents;	// hold class names
	private List<String> myLeftComponents;
	private List<String> myBottomComponents;
	
	public GameInfo(MainGameEngine engine, List<String> leftComponents, List<String> rightComponents, List<String> bottomComponents){
	    myEngine=engine;
	    myRightComponents=rightComponents;
	    myLeftComponents=leftComponents;
	    myBottomComponents=bottomComponents;
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

