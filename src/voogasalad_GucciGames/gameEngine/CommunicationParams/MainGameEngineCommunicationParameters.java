package voogasalad_GucciGames.gameEngine.CommunicationParams;

import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleMap;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

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
