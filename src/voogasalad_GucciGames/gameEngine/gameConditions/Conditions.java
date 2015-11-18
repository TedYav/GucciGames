
package voogasalad_GucciGames.gameEngine.gameConditions;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Conditions {
	protected ConditionParams myParams;

	public Conditions(ConditionParams condParams, BasicParameters params) {
		myParams = condParams;
	}

	public abstract void execute(BasicParameters params);

}
