package voogasalad_GucciGames.gameEngine.CommunicationParams;

import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleManager;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class BasicParameters extends CommunicationParameters{

	// Classes which extend this will be used to share information between the front and back end
	private MapObject myCalledMe;
	private ActionToRuleManager myActionToRuleMap;
	private MainGameEngine myEngine;
	
	public BasicParameters(MapObject calledMe, ActionToRuleManager actionToRuleMap,
			MainGameEngine engine){
		this.myCalledMe = calledMe;
		this.myActionToRuleMap = actionToRuleMap;
		this.myEngine = engine;
	}
	
	public BasicParameters(ActionToRuleManager actionToRuleMap,
			MainGameEngine engine){
		this.myCalledMe = null;
		this.myActionToRuleMap = actionToRuleMap;
		this.myEngine = engine;
	}
	
	public BasicParameters(BasicParameters params,
			MapObject calledMe){
		this(calledMe, params.getActionToRuleMap(),params.getEngine());
	}
	
	public BasicParameters(BasicParameters params){
		this(params,params.getCalledMe());
	}
	
	public MainGameEngine getEngine(){
		return this.myEngine;
	}
	
	public int getTurn(){
		return myEngine.getTurn();
	}

	public MapObject getCalledMe() {
		return myCalledMe;
	}

	public ActionToRuleManager getActionToRuleMap() {
		return myActionToRuleMap;
	}

}
