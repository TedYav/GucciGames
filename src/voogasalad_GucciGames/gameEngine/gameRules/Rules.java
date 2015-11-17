
package voogasalad_GucciGames.gameEngine.gameRules;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Rules {
	//interface of map instead of class?
	public Rules(CommunicationParams theBigMap, Map<String,Object> ruleArgs){}
	public abstract Boolean executeRules(int playerID);

}
