
package voogasalad_GucciGames.gameEngine.gameRules;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Rules {
	//interface of map instead of class?
	public Rules(){}
	public abstract Boolean executeRules(CommunicationParams communicationParams, int playerID);

}
