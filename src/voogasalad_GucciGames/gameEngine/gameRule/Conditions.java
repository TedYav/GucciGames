
package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRule.oucomes.Outcome;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Conditions {
	private static List<Conditions> RulesInEffect;

	public Conditions(List<GamePlayerPerson> players, Outcome outcome) {
		RulesInEffect = new ArrayList<Conditions>();
	}

	public void addRule(Conditions ruleName) {
		RulesInEffect.add(ruleName);
	}

	public void applyRules() {
		Iterator<Conditions> ruleIterator = RulesInEffect.iterator();
		while (ruleIterator.hasNext()) {
			ruleIterator.next().execute();
		}
	}

	public abstract void execute();

}
