package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.game;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;

public interface GameCondition {

	/**
	 * This method will generate a list which will assign an EndGameCondition to
	 * every player on index i. If the list returned is empty, then that
	 * indicates the rule has not resolved yet. Ideally, you want to use this
	 * method with hasConditionResolved() method to check whether it returned a
	 * true (which will guarantee this rule has resolved).
	 * 
	 * This method may be retired in the future for just evaluating the
	 * EndResult
	 * 
	 * @return a list of end game conditions
	 */
	public List<EndGameConditions> getConditionResolution();

	/**
	 * 
	 * @return whether a condition has resolved
	 */
	public boolean hasConditionResolved();

	/**
	 * This method actually decides what happens when the condition has
	 * resolved. For now, this method will call getConditionResolution() and 
	 * do something with that resolution.
	 * 
	 */
	public void evaluateEndResult();

}
