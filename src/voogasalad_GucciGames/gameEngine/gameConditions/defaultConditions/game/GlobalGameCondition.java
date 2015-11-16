package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.game;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;

//make real and null

/**
 * This condition is for conditions that requires full access to the map (will
 * be replaced by an interface later)
 * 
 * @author Efe Aras
 *
 */
public abstract class GlobalGameCondition implements GameCondition {

	protected GameMap myGameMap; // change it to private later

	public GlobalGameCondition(GameMap gameMap) {
		myGameMap = gameMap;
	}

}
