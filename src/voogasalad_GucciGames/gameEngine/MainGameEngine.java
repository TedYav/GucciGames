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
	private Goal myGlobalStatus;

	public MainGameEngine(AllPlayers gamePlayers, GlobalGameRule globalRule, GameMap gameMap) {

		myGamePlayers = gamePlayers;
		myCurrentTurn = 1;
		myGlobalRule = globalRule;
		myGameMap = gameMap;
	}

	public MainGameEngine(SomeData someData) {

	}

	public void takeTurn() {

		myGamePlayers.takeTurn(myCurrentTurn);
		// myGlobalStatus.checkSatisfied();
		checkTurnOutcome();
	}

	private void checkTurnOutcome() {
		// this will become very long as conditions are added .. re-factor
		String currentStatus = myGamePlayers.getActivePlayer(myCurrentTurn).getStatus();
		if (currentStatus.equals("LOSE")) {

		} else if (currentStatus.equals("WIN")) {
		} else if (currentStatus.equals("DRAW")) {
		} else
			myCurrentTurn++;
		// this method checks the status of a player's goal, and the global
		// game's goal
		// if the status for both is goalNotAchieved, it does not do anything
		// else, it halts the game and send an update to the front end

	}

	public int getCurrentTurn() {
		return this.myCurrentTurn;
	}

	public void incrementCurrentTurn() {
		this.myCurrentTurn++;
	}

	public void initialize(SomeData data) {

	}

	public void CreatGoal(List<String> names, List<Double> values) {
		myGlobalStatus.addRequirement(names, values);
	}

}
