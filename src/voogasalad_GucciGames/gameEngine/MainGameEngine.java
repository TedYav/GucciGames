package voogasalad_GucciGames.gameEngine;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRule.GlobalGameRule;
import voogasalad_GucciGames.gameEngine.gameRule.Goal.Goal;

// TODO: 
// *Store allowable tiles, units here -> UnitContainer, TileContainer
// *Rename AllPlayers to PlayerContainer
// *Allow for multiple maps -> MapContainer
// *Add Name to Engine -> so we know what game this is...
// *Decide if we want to serialize this class or create another just for the data
// 		*Note, this may be nice, but kind of pointless, since this IS the game... :)
// SEE THIS IMAGE: https://www.dropbox.com/sc/ouekq7n22pcm7uh/AAA35yvEEaprH9PkuDL5unk6a


public class MainGameEngine {

	private AllPlayers myGamePlayers;
	private int myCurrentTurn;
	private GlobalGameRule myGlobalRule;

	private GameMap myGameMap;
	private Goal myGlobalGoal;

	public MainGameEngine(AllPlayers gamePlayers, GlobalGameRule globalRule, GameMap gameMap){


		myGamePlayers = gamePlayers;
		myCurrentTurn = 1;
		myGlobalRule = globalRule;

		myGameMap = gameMap;
	}

	public MainGameEngine(SomeData someData) {
	
	}

	public void takeTurn() {

		myGamePlayers.takeTurn(myCurrentTurn);
		myGlobalGoal.checkSatisfied();
		checkGoalOutCome();
		myCurrentTurn++;

	}


	private void checkGoalOutCome() {
	//this method checks the status of a player's goal, and the global game's goal
	//if the status for both is goalNotAchieved, it does not do anything
	//else, it halts the game and send an update to the front end
	}

	public int getCurrentTurn(){
		return this.myCurrentTurn;
	}

	public void incrementCurrentTurn(){
		this.myCurrentTurn++;
	}

	public void initialize(SomeData data) {
		// TODO Auto-generated method stub
		
	}
	
	public void CreatGoal(List<String> names, List<Double> values){
		myGlobalGoal.addRequirement(names, values);
	}

}
