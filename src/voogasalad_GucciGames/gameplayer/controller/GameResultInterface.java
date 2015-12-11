package voogasalad_GucciGames.gameplayer.controller;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;

public interface GameResultInterface {

	/**
	 * This method returns a map that maps each playerID to an EndGameCondition.
	 * 
	 * @return a Map that maps each playerID to an EndGameCondition.
	 */
	public Map<Integer, EndGameConditions> getEachPlayerConditions();

	@Deprecated
	/**
	 * This method returned a map of player names to their scores. It turns out
	 * that it is wiser the Game Player should access the names of the players through their
	 * IDs.
	 * 
	 * @return
	 */
	public Map<String, Integer> getPlayerScores();

	@Deprecated
	/**
	 * This method should not be used because it assumes that there is only one
	 * winner. Ideally, every single data should be pulled from
	 * getEachPlayerConditions() since that will more accurately reflect the
	 * state of the players.
	 * 
	 * @return
	 */
	public int whoWon();
}
