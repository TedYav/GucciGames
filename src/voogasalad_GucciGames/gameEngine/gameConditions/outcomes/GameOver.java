package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;

public class GameOver extends Outcome{	
	boolean gameOver;
	
	public GameOver(boolean over){
		gameOver = over;
	};

	@Override
	ChangedParameters applyOutcome(BasicParameters params, ChangedParameters changedParams, int playerID) {
		params.getEngine().setEndLevel(true);
		changedParams.setNextLevel("");
		//params.getEngine().changeLevel(myDestination);
		System.out.println("setgame="+params.getEngine().hasLevelEnded());
		return changedParams;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
}
