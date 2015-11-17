package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.MovablePlayerCharacteristic;
import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleMap;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class CommunicationParams {
	
	// Classes which extend this will be used to share information between the front and back end
	private AllPlayers myPlayers;
	private GameMap myGameMap;
	private MapObject myCalledMe;
	private ActionToRuleMap myActionToRuleMap;
	private MovablePlayerCharacteristic myMovable;
	
	public CommunicationParams(AllPlayers players, GameMap gameMap,
			MapObject calledMe, ActionToRuleMap actionToRuleMap, MovablePlayerCharacteristic movePerson){ 
		this.myPlayers = players;
		this.myGameMap = gameMap;
		this.myCalledMe = calledMe;
		this.myActionToRuleMap = actionToRuleMap;
		this.myMovable = movePerson;
		
	}
	
	public CommunicationParams(CommunicationParams params){
		this(params.getPlayers(), params.getGameMap(), params.getCalledMe(), params.getActionToRuleMap(), params.myMovable);
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
	
	public MovablePlayerCharacteristic getMovablePerson() {
		return myMovable;
	}
}
