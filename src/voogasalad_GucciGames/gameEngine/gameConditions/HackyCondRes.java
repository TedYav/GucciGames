/**
 *
 */
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sally Al will replace this class with a property file and a proper
 *         reflection class.
 *
 */
public class HackyCondRes {

	private Map<String, String> map;

	protected HackyCondRes() {
		map = new HashMap<String, String>();
		map.put("PlayerUnitCondition", "voogasalad_GucciGames.gameEngine.gameRule.defaultConditions.player.PlayerUnitCondition");

	}

	protected boolean containsKey(String condName) {
		return map.containsKey(condName);
	}

	protected String getValue(String condName) {
		return map.get(condName);
	}
}
