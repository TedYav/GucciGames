package voogasalad_GucciGames.gameEngine;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.game.GlobalGameCondition;
import voogasalad_GucciGames.gameEngine.gamePlayer.ATurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.DefaultTurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.TurnCounter;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public class MainGameEngine implements GameEngineToGamePlayerInterface {

	private AllPlayers myGamePlayers;
	private TurnCounter myCurrentTurnCounter;
	private ATurnDecider myTurnDecider;

	private ConditionHandler myConditionHandler;


	private String myName;
	private GameMap myGameMap;

	public String getName() {
		return myName;
	}

	public MainGameEngine(AllPlayers gamePlayers, GlobalGameCondition globalRule, GameMap gameMap) {

		myGamePlayers = gamePlayers;
		myGameMap = gameMap;
		myCurrentTurnCounter = new TurnCounter();
		myTurnDecider = new DefaultTurnDecider(gamePlayers.getNumberOfPlayers(), myCurrentTurnCounter);

	}


	@Override
	public String getGameName() {

		return myName;
	}

	@Override
	public void endTurn() {

		myConditionHandler.checkAllConditions();

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
		// TODO Auto-generated method stub
		return myGamePlayers.getInitialState();
	}

}
