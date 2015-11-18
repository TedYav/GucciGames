package voogasalad_GucciGames.gameEngine.CommunicationParams;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleMap;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class MainGameEngineCommunicationParams extends BasicParameters{

	private MainGameEngine myMainEngine;

	public MainGameEngineCommunicationParams(AllPlayers players, GameMap gameMap, MapObject calledMe,
			ActionToRuleMap actionToRuleMap, MainGameEngine myEngine) {
		super(players, gameMap, calledMe, actionToRuleMap);
		
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
