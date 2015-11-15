/**
 *
 */
package voogasalad_GucciGames;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.gameRule.Conditions;

/**
 *
 * @author Sally Al
 *
 */
public class ConditionsInEffect {
	private List<Conditions> list;

	public ConditionsInEffect() {
		list = new ArrayList<Conditions>();
	}

	public void addRule(Conditions condition) {
		list.add(condition);
	}
}
