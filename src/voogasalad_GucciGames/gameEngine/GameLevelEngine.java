package voogasalad_GucciGames.gameEngine;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameData.wrapper.GameStats;
import voogasalad_GucciGames.gameData.wrapper.IGameLevelToGamePlayer;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GameParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GameResult;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.ATurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.DefaultTurnDecider;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.TurnCounter;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerScore;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;


public class GameLevelEngine implements IGameLevelToGamePlayer {

	private AllPlayers myGamePlayers;
	private TurnCounter myCurrentTurnCounter;
	private ATurnDecider myTurnDecider;
	private int mapDimensions;
	private int myMapWidth;
	private int myMapHeight;
	private String myName;
	private String myNextLevelName;
	private boolean myChoosability;
	private boolean hasLevelEnded;
	private GameStats myGameStats;
	
	private List<Double> scoresList;

	
	public void setLevelName(String name){
		myName = name;
	}
	
	@Override
	public String getLevelName() {
		return myName;
	}
	
//	public void setNextLevelName(String lname){
//		this.myNextLevelName = lname;
//	}
//	
//	@Override
//	public String getNextLevel(){
//		return this.myNextLevelName;
//	}

	public GameLevelEngine(AllPlayers gamePlayers) {
		myGamePlayers = gamePlayers;
		myCurrentTurnCounter = new TurnCounter();
		myTurnDecider = new DefaultTurnDecider(myGamePlayers, myCurrentTurnCounter);
		hasLevelEnded = false;

//		myName = "Game " + Math.round((Math.random()*10000));
	}

	@Deprecated
	public String getGameName() {
		return myName;
	}


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


	public List<PlayerMapObjectInterface> getInitialState() {
		return myGamePlayers.getInitialState();
	}


	public int getTurnPlayerID() {
		return myTurnDecider.decideTurn();
	}


	public GridCoordinateParameters getPossibleCoordinates(String action, PlayerMapObjectInterface myMapObject) {
		return null;

	}
	////////
	/*
	public void createTestCondition() {
		List<Integer> pl = new ArrayList<Integer>();
		pl.add(0);

		//BasicParameters comParams = new BasicParameters(myGamePlayers, null, null);
		BasicParameters comParams = new BasicParameters(null,this);

		endTurn();
		endTurn();
	}
	*/

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


	public ChangedParameters performAction(String action, PlayerMapObjectInterface mapObject,
			ATargetCoordinate target) {

		return null;

	}

	public int getMapWidth() {
		// TODO Auto-generated method stub
		return myMapWidth;
	}

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


	public GameParametersInterface getGameParameters() {

		// TODO Auto-generated method stub
		GameParameters pp= new GameParameters();
		if(myGamePlayers.getNumberOfPlayers() != 2){
			//pp.setGameWon(false);
		}
		else{
			//pp.setGameWon(true);
		}

		pp.setCurrentTurnPlayer(getTurnPlayerID());
		pp.setGameName(myName);

		//change it before demo
		pp.setMapHeight(8);
		pp.setMapWidth(8);

		//Map<String, Double> score = new HashMap<String, Double>();
		//Map<String, Integer> scoreID = new HashMap<String, Integer>();

		List<Integer> myIDs = myGamePlayers.getAllIds();
		Collections.sort(myIDs);

		for(int i = 1; i < myIDs.size(); i++){
			//score.put("Player" + myIDs.get(i), myGamePlayers.getActivePlayer(myIDs.get(i)).getMapObjects().size() * 500.0);
			//scoreID.put("Player" + myIDs.get(i), myGamePlayers.getActivePlayer(myIDs.get(i)).getMapObjects().size() * 500);
			GamePlayerPerson gp = myGamePlayers.getPlayerById(myIDs.get(i));
			if (gp.hasCharacteristic("PlayerScore") && myGameStats.contains(myIDs.get(i))){	
				double score = ((PlayerScore) myGameStats.getCharacteristics(myIDs.get(i)).get("PlayerScore")).getScore();
				((PlayerScore) gp.getCharacteristics("PlayerScore")).setScore(score+myGamePlayers.getActivePlayer(myIDs.get(i)).getMapObjects().size() * 500);
			}
			else{
				gp.addCharacterstic("PlayerScore", new PlayerScore(myGamePlayers.getActivePlayer(myIDs.get(i)).getMapObjects().size() * 500));
			}
			
		}


		//pp.setScore(score);

		GameResult game = new GameResult();


		Map<Integer, EndGameConditions> myConds = new HashMap<Integer, EndGameConditions>();
		for(int i = 1; i < myIDs.size(); i++){
			myConds.put(myIDs.get(i), myGamePlayers.getActivePlayer(myIDs.get(i)).getStatusCondition());
		}


		game.setPlayerConditions(myConds);
		//game.setFinalScores(scoreID);

		for(int i = 1; i < myIDs.size(); i++){
			if(myConds.get(myIDs.get(i)).equals(EndGameConditions.WIN)){
				game.setWinner(myIDs.get(i));

			}
		}


		pp.setGameResult(game);

		return pp;
	}

	@Override
	public boolean hasLevelEnded() {
		return hasLevelEnded;
	}

//	public void setStartLevel(){
//		hasLevelEnded = true;
//	}
//	
//	public void endStartLevel(){
//		hasLevelEnded = false;
//	}
	
	public void setEndLevel(boolean gameWon) {
		this.hasLevelEnded = gameWon;
	}
	
	@Override
	public boolean isMyChoosability() {
		return myChoosability;
	}

	public void setMyChoosability(boolean myChoosability) {
		this.myChoosability = myChoosability;
	}
	
	public void setGameStats(GameStats clazz){
		this.myGameStats = clazz;
	}
	
	public boolean getGameOver(){
//		if (getNextLevel() == ""){
//			return true;
//		}		
		return this.hasLevelEnded;
	}
	
}
