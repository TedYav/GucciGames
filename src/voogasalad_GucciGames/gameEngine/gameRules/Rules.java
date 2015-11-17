
package voogasalad_GucciGames.gameEngine.gameRules;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Rules {
	//interface of map instead of class?
	public Rules(CommunicationParams communicationParams, List<Object> ruleArgs){}
	public abstract Boolean executeRules(int playerID);

}
