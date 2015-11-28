package voogasalad_GucciGames.gameEngine.CommunicationParams;

import voogasalad_GucciGames.gameEngine.MainGameEngine;

public class MainGameEngineCommunicationParameters extends CommunicationParameters{

	private MainGameEngine myMainEngine;

	public MainGameEngineCommunicationParameters(MainGameEngine myEngine) {

		myMainEngine = myEngine;

		// TODO Auto-generated constructor stub
	}

	public int getActivePlayer(){
		return myMainEngine.getActivePlayer();
	}

	public int getTurn() {
		return myMainEngine.getTurn();
	}


}
