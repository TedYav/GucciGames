/**
 *
 */
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;

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

	public void evaluateAllConditions( BasicParameters params) {
		for (String key : map.keySet()) {
			System.out.println("condition: "+key);
			map.get(key).execute( params);
		}
	}

}
