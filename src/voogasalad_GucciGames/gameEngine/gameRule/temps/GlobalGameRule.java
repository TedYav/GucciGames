package voogasalad_GucciGames.gameEngine.gameRule.temps;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gameRule.EndGameConditions;

//make real and null
public abstract class GlobalGameRule extends GameConditions {

	protected GameMap myGameMap; // change it to private later

	public GlobalGameRule(GameMap gameMap) {
		myGameMap = gameMap;
	}

	public GlobalGameRule() {
	}

	public abstract List<EndGameConditions> gameEnded();

}
