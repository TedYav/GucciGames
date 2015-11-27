
package voogasalad_GucciGames.gameEngine.gameConditions;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Conditions {
	protected ConditionParams myParams;

	public Conditions(ConditionParams condParams) {
		myParams = condParams;
	}

	public abstract Boolean execute(GamePlayerPerson player);

}
