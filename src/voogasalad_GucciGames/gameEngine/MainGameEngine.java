package voogasalad_GucciGames.gameEngine;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRule.defaultConditions.game.GameCondition;
import voogasalad_GucciGames.gameEngine.gameRule.defaultConditions.game.GlobalGameCondition;

public class MainGameEngine {

	private AllPlayers myGamePlayers;
	private int myCurrentTurn;
	private GameCondition myGlobalRule;
	private String myName;




	private GameMap myGameMap;


	public String getName(){
		return myName;
	}

	public MainGameEngine(AllPlayers gamePlayers, GlobalGameCondition globalRule, GameMap gameMap) {

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

		myGlobalRule.evaluateEndResult();


/*		// this will become very long as conditions are added .. re-factor
		String currentStatus = myGamePlayers.getActivePlayer(myCurrentTurn).getStatus();
		if (currentStatus.equals("LOSE")) {

		} else if (currentStatus.equals("WIN")) {
		} else if (currentStatus.equals("DRAW")) {
		} else
			myCurrentTurn++;
		// this method checks the status of a player's goal, and the global
		// game's goal
		// if the status for both is goalNotAchieved, it does not do anything
		// else, it halts the game and send an update to the front end  */

	}

	public int getCurrentTurn() {
		return this.myCurrentTurn;
	}

	public void incrementCurrentTurn() {
		this.myCurrentTurn++;
	}

	public void initialize(SomeData data) {

	}



}
