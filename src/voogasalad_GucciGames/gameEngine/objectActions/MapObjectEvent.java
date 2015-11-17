package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParams;
import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleMap;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al class based on template method pattern.
 *
 */
public abstract class MapObjectEvent {
	private String myName;

	public MapObjectEvent(String actionName) {
		myName = actionName;
	}

	// must keep executeAction() final
	public final CommunicationParams executeAction(CommunicationParams params, int playerID) {
		Boolean check = checkRules(playerID, params);
		if (check) {
			return execute(params);
		}
		return new EmptyParams();

	}

	private Boolean checkRules(int playerID, CommunicationParams params) {
		Boolean ruletest = false;
		ActionToRuleMap mapobj = params.getActionToRuleMap();
		if (mapobj.contains(myName)) {
			List<Rules> list = mapobj.getKey(myName);
			if (list != null) {
				Iterator<Rules> ruleItr = list.iterator();
				while (ruleItr.hasNext()) {
					ruletest = ruleItr.next().executeRules(params, playerID);
					if (ruletest == false) {
						return ruletest;
					}
				}
			}
		}

		return ruletest;
	}

	protected abstract CommunicationParams execute(CommunicationParams params);

}
