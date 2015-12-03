
package voogasalad_GucciGames.gameEngine.gameRules;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Rules {
	public Rules(RuleParams myParams, BasicParameters params){}
	public Rules(){};
	public abstract Boolean executeRules(BasicParameters communicationParams);

}
