package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;


import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleMap;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class CommunicationParams {
	
	// Classes which extend this will be used to share information between the front and back end
	private AllPlayers myPlayers;
	private GameMap myGameMap;
	private List<MapObject> myLocations;
	private MapObject myCurrentActiveMapObject;
	
	
	private MapObject myCalledMe;
	private ActionToRuleMap myActionToRuleMap;

	public CommunicationParams(AllPlayers players, GameMap gameMap,
			MapObject calledMe, ActionToRuleMap actionToRuleMap){ 
		this.myPlayers = players;
		this.myGameMap = gameMap;
		this.myCalledMe = calledMe;
		this.myActionToRuleMap = actionToRuleMap;
		
	}
	
	public CommunicationParams(CommunicationParams params){
		this(params.getPlayers(), params.getGameMap(), params.getCalledMe(), params.getActionToRuleMap());
	}

	public AllPlayers getPlayers() {
		return myPlayers;
	}

	public GameMap getGameMap() {
		return myGameMap;
	}

	public MapObject getCalledMe() {
		return myCalledMe;
	}

	public ActionToRuleMap getActionToRuleMap() {
		return myActionToRuleMap;
	}	
}
