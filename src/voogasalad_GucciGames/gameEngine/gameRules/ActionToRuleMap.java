package voogasalad_GucciGames.gameEngine.gameRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sally Al
 * purpose: maps actions to their rules.
 *
 */
public class ActionToRuleMap {
	private Map<String, List<Rules>> map;

	public ActionToRuleMap() {
		map = new HashMap<String, List<Rules>>();
	}

	public Boolean contains(String actionName) {
		return map.containsKey(actionName);
	}

	public List<Rules> getKey(String actionName) {
		if (map != null) {
			if (map.containsKey(actionName)) {
				return map.get(actionName);
			}
		}
		return null;

	}

	public void put(String actionName, Rules rule) {
		if (map.containsKey(actionName)) {
			if (map.get(actionName) != null) {
				List<Rules> temp = map.get(actionName);
				temp.add(rule);
				map.put(actionName, temp);
			} else {
				createList(actionName, rule);
			}
		} else {
			createList(actionName, rule);
		}

	}

	private void createList(String actionName, Rules rule) {
		List<Rules> list = new ArrayList<Rules>();
		list.add(rule);
		map.put(actionName, list);
	}
}
