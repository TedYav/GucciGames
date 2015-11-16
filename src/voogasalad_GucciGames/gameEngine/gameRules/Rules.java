
package voogasalad_GucciGames.gameEngine.gameRules;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Rules {
	//interface of map instead of class?
	public Rules(MapObjectType theBigMap, Map<String,Object> ruleArgs){}
	protected abstract Boolean executeRules(List<Object> list);

}
