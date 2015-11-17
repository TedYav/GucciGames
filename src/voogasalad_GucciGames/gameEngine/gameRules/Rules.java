
package voogasalad_GucciGames.gameEngine.gameRules;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Rules {
	public Rules(){}
	public abstract Boolean executeRules(CommunicationParams communicationParams, int playerID);

}
