/**
 *
 */
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sally Al
 *
 */
public class ConditionHandler {
	private Map<String, Conditions> map;

	public ConditionHandler() {
		map = new HashMap<String, Conditions>();
	}

	public void addCondition(String condName, Conditions condition) {
		map.put(condName, condition);
	}

	public Conditions getCondition(String condName) {
		return map.get(condName);
	}

	public void evaluateAllConditions() {
		for (String key : map.keySet()) {
			System.out.println(key);
			map.get(key).execute();
		}
	}

}
