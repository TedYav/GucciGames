package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleMap;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al class based on template method pattern.
 *
 */
public abstract class Action {
	private String myName;

	public Action(String actionName) {
		myName = actionName;
	}

	// must keep executeAction() final
	public final void executeAction(ActionToRuleMap mapobj, int playerID) {
		Boolean check = checkRules(mapobj, playerID);
		if (check) {
			execute();
		}

	}

	private Boolean checkRules(ActionToRuleMap mapobj, int playerID) {
		Boolean ruletest = false;
		if (mapobj.contains(myName)) {
			List<Rules> list = mapobj.getKey(myName);
			if (list != null) {
				Iterator<Rules> ruleItr = list.iterator();
				while (ruleItr.hasNext()) {
					ruletest = ruleItr.next().executeRules(playerID);
					if (ruletest == false) {
						return ruletest;
					}
				}
			}
		}

		return ruletest;
	}

	protected abstract void execute();

}
