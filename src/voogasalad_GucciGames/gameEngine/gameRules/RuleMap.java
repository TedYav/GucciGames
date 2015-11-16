package voogasalad_GucciGames.gameEngine.gameRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sally Al
 *
 */
public class RuleMap {
	private Map<String, List<Rules>> map;

	public RuleMap() {
		map = new HashMap<String, List<Rules>>();
	}

	public Boolean contains(String ruleName) {
		return map.containsKey(ruleName);
	}

	public void put(String ruleName, Rules rule) {
		if (map.containsKey(ruleName)) {
			if (map.get(ruleName) != null) {
				List<Rules> temp = map.get(ruleName);
				temp.add(rule);
				map.put(ruleName,temp);
			} else {
				createList(ruleName, rule);
			}
		} else {
			createList(ruleName, rule);
		}

	}

	private void createList(String ruleName, Rules rule) {
		List<Rules> list = new ArrayList<Rules>();
		list.add(rule);
		map.put(ruleName, list);
	}
}
