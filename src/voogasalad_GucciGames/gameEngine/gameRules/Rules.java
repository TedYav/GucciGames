
package voogasalad_GucciGames.gameEngine.gameRules;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Rules {
	public Rules(RuleParams myParams, BasicParameters params){}
	public abstract Boolean executeRules(BasicParameters communicationParams, int playerID);

}
