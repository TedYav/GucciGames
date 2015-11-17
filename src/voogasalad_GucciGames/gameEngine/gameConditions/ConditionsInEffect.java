/**
 *
 */
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.ArrayList;
import java.util.List;

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
