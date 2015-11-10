package voogasalad_GucciGames.gameEngine;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRule.GlobalGameRule;
import voogasalad_GucciGames.gameEngine.gameRule.Goal.Goal;

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

	public void CreatGoal(List<String> names, List<Double> values){
		myGlobalGoal.addRequirement(names, values);
	}

}
