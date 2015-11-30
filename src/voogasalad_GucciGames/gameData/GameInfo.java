package voogasalad_GucciGames.gameData;

import java.util.List;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.MainGameEngine;

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
	
	public GameInfo(MainGameEngine engine, List<String> leftComponents, List<String> rightComponents){
	    myEngine=engine;
	    myRightComponents=rightComponents;
	    myLeftComponents=leftComponents;
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
}
