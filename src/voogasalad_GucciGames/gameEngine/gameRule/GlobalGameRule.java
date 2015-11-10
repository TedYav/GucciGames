package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;

//make real and null
public abstract class GlobalGameRule extends GameRule{

	protected GameMap myGameMap; //change it to private later
	
	public GlobalGameRule(GameMap gameMap){
		myGameMap = gameMap;
	}
	
	public abstract List<EndGameConditions> gameEnded();
	
}
