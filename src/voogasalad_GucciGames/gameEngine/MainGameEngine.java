package voogasalad_GucciGames.gameEngine;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.MainGameEngineCommunicationParams;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionHandler;
import voogasalad_GucciGames.gameEngine.gameConditions.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.game.GlobalGameCondition;
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

	public MainGameEngine(AllPlayers gamePlayers, GlobalGameCondition globalRule) {

		myGamePlayers = gamePlayers;
		myCurrentTurnCounter = new TurnCounter();
		myTurnDecider = new DefaultTurnDecider(gamePlayers.getNumberOfPlayers(), myCurrentTurnCounter);
		myConditionHandler=new ConditionHandler();
	}

	@Override
	public String getGameName() {

		return myName;
	}

	@Override
	public void endTurn() {

		myConditionHandler.evaluateAllConditions();

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
    public int getTurnPlayerID () {
        // TODO Auto-generated method stub
        return 0;
    }
	@Override
	public GridCoordinateParameters getPossibleCoordinates(String action, PlayerMapObjectInterface myMapObject) {
		return ((MapObject) myMapObject).performAction(action, new MainGameEngineCommunicationParams(myGamePlayers, null, ((MapObject) myMapObject), null, this));
		
		
		
	}

}
