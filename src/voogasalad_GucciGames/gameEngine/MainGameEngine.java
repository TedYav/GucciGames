package voogasalad_GucciGames.gameEngine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.ActionToGamePlayerParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.LocationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MainGameEngineCommunicationParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionHandler;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionParams;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionsFactory;
import voogasalad_GucciGames.gameEngine.gamePlayer.ATurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.DefaultTurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.TurnCounter;
import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleManager;
import voogasalad_GucciGames.gameEngine.gameRules.RuleFactory;
import voogasalad_GucciGames.gameEngine.gameRules.RuleParams;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public class MainGameEngine implements GameEngineToGamePlayerInterface {

	private AllPlayers myGamePlayers;
	private TurnCounter myCurrentTurnCounter;
	private ATurnDecider myTurnDecider;
	private ConditionHandler myConditionHandler;

	private ActionToRuleManager myRuleManager;
	private int mapDimensions;
	private String myName;

	public String getName() {
		return myName;
	}

	public MainGameEngine(AllPlayers gamePlayers) {
		myGamePlayers = gamePlayers;
		myCurrentTurnCounter = new TurnCounter();
		myTurnDecider = new DefaultTurnDecider(gamePlayers.getNumberOfPlayers(), myCurrentTurnCounter);
		myConditionHandler = new ConditionHandler();

	}

	@Override
	public String getGameName() {

		return myName;
	}

	@Override
	public void endTurn() {

		BasicParameters comParams = new BasicParameters(myGamePlayers, null, null);
		myConditionHandler.evaluateAllConditions(comParams);
		System.out.println("end of condition evaluation");
		System.out.println("----");
		myCurrentTurnCounter.update();

	}

	public int getActivePlayer() {
		return myTurnDecider.decideTurn();
	}

	public int getTurn() {

		return myCurrentTurnCounter.getCurrentTurn();
	}

	@Override
	public List<PlayerMapObjectInterface> getInitialState() {
		return myGamePlayers.getInitialState();
	}

	@Override
	public int getTurnPlayerID() {
		return 0;
	}

	@Override
	public GridCoordinateParameters getPossibleCoordinates(String action, PlayerMapObjectInterface myMapObject) {
		return ((MapObject) myMapObject).performRequest(action, new BasicParameters(this, ((MapObject) myMapObject)));

	}

	////////
	public void createTestCondition() {
		List<Integer> pl = new ArrayList<Integer>();
		pl.add(0);
		ConditionParams condParams = new ConditionParams("PlayerUnitCondition", "player", pl, null);
		ConditionsFactory factory = new ConditionsFactory();
		BasicParameters comParams = new BasicParameters(myGamePlayers, null, null);

		try {
			Conditions condition = factory.createCondition(condParams, comParams);
			myConditionHandler.addCondition("PlayerUnitCondition", condition);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		endTurn();
		endTurn();
	}

	public void testRules() {
		System.out.println("create rules");
		RuleFactory factory = new RuleFactory();
		RuleParams params = new RuleParams("move", null, null);
		ActionToRuleManager manager = new ActionToRuleManager();
		BasicParameters comParams = new BasicParameters(myGamePlayers, null, manager);
		try {
			factory.createRule(params, comParams);
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// execute rule:
		System.out.println("test rules");
		List<Rules> rules = manager.getRulesForAction("move");
		for (int i = 0; i < rules.size(); i++) {
			rules.get(i).executeRules(comParams, 0);
		}

	}

	public AllPlayers getPlayers() {
		// TODO Auto-generated method stub
		return myGamePlayers;
	}

	public ActionToRuleManager getActionToRuleManager() {
		// TODO Auto-generated method stub
		return this.myRuleManager;
	}

	public int getMapDimensions() {
		return this.mapDimensions;
	}

	@Override
	public ActionToGamePlayerParameters performAction(String action, PlayerMapObjectInterface mapObject,
			ATargetCoordinate target) {
		// TODO Auto-generated method stub
		return ((MapObject) mapObject).performAction(action,
				new LocationParams(new BasicParameters(this, ((MapObject) mapObject)),
						target.getListOfCoordinates().get(0),
						this.getPlayers().getPlayerById(((MapObject) mapObject).getPlayerID()).getMyMovable()));
	}
}
