package voogasalad_GucciGames.gameEngine;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GameParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GameResult;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.ATurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.DefaultTurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.TurnCounter;
import voogasalad_GucciGames.gameEngine.gameRules.RuleFactory;
import voogasalad_GucciGames.gameEngine.gameRules.RuleParams;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;

public class MainGameEngine implements GameEngineToGamePlayerInterface {

	private AllPlayers myGamePlayers;
	private TurnCounter myCurrentTurnCounter;
	private ATurnDecider myTurnDecider;
	private int mapDimensions;
	private int myMapWidth;
	private int myMapHeight;
	private String myName;
	private boolean gameWon;

	public String getName() {
		return myName;
	}

	public MainGameEngine(AllPlayers gamePlayers) {
		myGamePlayers = gamePlayers;
		myCurrentTurnCounter = new TurnCounter();
		myTurnDecider = new DefaultTurnDecider(myGamePlayers, myCurrentTurnCounter);


		myName = "Game " + Math.round((Math.random()*10000));
	}
	@Override
	@Deprecated
	public String getGameName() {
		return myName;
	}

	@Override
	public GameParametersInterface endTurn() {
		//check game conditions
		myCurrentTurnCounter.update();
		myTurnDecider.updateActivePlayer();
		myGamePlayers.reset();
		return getGameParameters();
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
		return myTurnDecider.decideTurn();
	}

	@Override
	public GridCoordinateParameters getPossibleCoordinates(String action, PlayerMapObjectInterface myMapObject) {
		return null;

	}
	////////
	public void createTestCondition() {
		List<Integer> pl = new ArrayList<Integer>();
		pl.add(0);

		//BasicParameters comParams = new BasicParameters(myGamePlayers, null, null);
		BasicParameters comParams = new BasicParameters(null,this);

		endTurn();
		endTurn();
	}
	public void testRules() {
		System.out.println("create rules");
		RuleFactory factory = new RuleFactory();
		RuleParams params = new RuleParams("move", null, null);
		//BasicParameters comParams = new BasicParameters(myGamePlayers, null, manager);
		BasicParameters comParams = new BasicParameters(null,this);
		try {
			factory.createRule(params, comParams);
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}
	public AllPlayers getPlayers() {
		// TODO Auto-generated method stub
		return myGamePlayers;
	}

	/*
	 * Deprecated this because we need to support more than square maps. -- Ted :)
	 */
	@Deprecated
	public int getMapDimensions() {
		return mapDimensions;
	}

	@Override
	public ChangedParameters performAction(String action, PlayerMapObjectInterface mapObject,
			ATargetCoordinate target) {

		return null;

	}
	@Override
	public int getMapWidth() {
		// TODO Auto-generated method stub
		return myMapWidth;
	}
	@Override
	public int getMapHeight() {
		// TODO Auto-generated method stub
		return myMapHeight;
	}

	public void setMapWidth(int width){
		myMapWidth = width;
	}

	public void setMapHeight(int height){
		myMapHeight = height;
	}

	@Override
	public GameParametersInterface getGameParameters() {
		// TODO Auto-generated method stub
		GameParameters pp= new GameParameters();
		if(myGamePlayers.getNumberOfPlayers() != 2){
			pp.setGameWon(false);
		}
		else{
			pp.setGameWon(true);
		}

		pp.setCurrentTurnPlayer(getTurnPlayerID());
		pp.setGameName(myName);

		//change it before demo
		pp.setMapHeight(8);
		pp.setMapWidth(8);

		Map<String, Double> score = new HashMap<String, Double>();
		Map<String, Integer> scoreID = new HashMap<String, Integer>();

		List<Integer> myIDs = myGamePlayers.getAllIds();
		Collections.sort(myIDs);

		for(int i = 1; i < myIDs.size(); i++){
			score.put("Player" + myIDs.get(i), myGamePlayers.getActivePlayer(myIDs.get(i)).getMapObjects().size() * 500.0);
			scoreID.put("Player" + myIDs.get(i), myGamePlayers.getActivePlayer(myIDs.get(i)).getMapObjects().size() * 500);		}


		pp.setScore(score);

		GameResult game = new GameResult();


		Map<Integer, EndGameConditions> myConds = new HashMap<Integer, EndGameConditions>();
		for(int i = 1; i < myIDs.size(); i++){
			myConds.put(myIDs.get(i), myGamePlayers.getActivePlayer(myIDs.get(i)).getStatusCondition());
		}


		game.setPlayerConditions(myConds);
		game.setFinalScores(scoreID);

		for(int i = 1; i < myIDs.size(); i++){
			if(myConds.get(myIDs.get(i)).equals(EndGameConditions.WIN)){
				game.setWinner(myIDs.get(i));

			}
		}


		pp.setGameResult(game);

		return pp;
	}
	public void setName(String name) {
		myName = name;
	}

	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}
}
