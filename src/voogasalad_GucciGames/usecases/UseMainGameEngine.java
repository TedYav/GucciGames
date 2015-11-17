package voogasalad_GucciGames.usecases;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.SomeData;
import voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.game.GlobalGameCondition;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;

public class UseMainGameEngine {

	private AllPlayers myGamePlayers;
	private int myCurrentTurn;
	private GlobalGameCondition myGlobalRule;

	private GameMap myGameMap;

	public UseMainGameEngine(AllPlayers gamePlayers, GlobalGameCondition globalRule, GameMap gameMap){


		myGamePlayers = gamePlayers;
		myCurrentTurn = 1;
		myGlobalRule = globalRule;

		myGameMap = gameMap;
	}

	public UseMainGameEngine(SomeData someData) {

	}

	public void takeTurn() {

		myGamePlayers.takeTurn(myCurrentTurn);
		myCurrentTurn++;

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

}
