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
public class ConditionsCreated {
	private Map<String, Conditions> map;

	public ConditionsCreated() {
		map = new HashMap<String, Conditions>();
	}

	public void addCondition(String condName, Conditions condition) {
		map.put(condName, condition);
	}

	public Conditions getCondition(String condName) {
		return map.get(condName);
	}

	// can move this later to MaingameEngine.java and do the loop there.
	// To do so, need this map to be stored in CommunicationParams.java
	public void loop() {
		for (String key : map.keySet()) {
			map.get(key).execute();
		}
	}

}
