package voogasalad_GucciGames.gameEngine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MainGameEngineCommunicationParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionHandler;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionParams;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionsFactory;
import voogasalad_GucciGames.gameEngine.gameConditions.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.ATurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.DefaultTurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.TurnCounter;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public class MainGameEngine implements GameEngineToGamePlayerInterface {

	private AllPlayers myGamePlayers;
	private TurnCounter myCurrentTurnCounter;
	private ATurnDecider myTurnDecider;
	private ConditionHandler myConditionHandler;

	private String myName;

	public String getName() {
		return myName;
	}

	public MainGameEngine(AllPlayers gamePlayers) {

		myGamePlayers = gamePlayers;
		myCurrentTurnCounter = new TurnCounter();
		myTurnDecider = new DefaultTurnDecider(gamePlayers.getNumberOfPlayers(), myCurrentTurnCounter);
		myConditionHandler = new ConditionHandler();
		createTestCondition();
	}

	@Override
	public String getGameName() {

		return myName;
	}

	private void createTestCondition() {
		List<Integer> pl = new ArrayList<Integer>();
		pl.add(0);
		ConditionParams condParams = new ConditionParams("PlayerUnitCondition", "player", pl, null);
		ConditionsFactory factory = new ConditionsFactory();
		// NOTE: this was this:
		// BasicParameters comParams= new BasicParameters(myGamePlayers, null,
		// null, null);
		// removed 3rd null to remove compile error

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
		return ((MapObject) myMapObject).performAction(action, new MainGameEngineCommunicationParameters(this));

	}

}
