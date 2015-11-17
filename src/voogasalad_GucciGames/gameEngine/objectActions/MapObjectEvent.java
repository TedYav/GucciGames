package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParameters;
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
	public final CommunicationParameters executeAction(CommunicationParameters params, int playerID) {
		Boolean check = checkRules(playerID,params);
		if (check) {
			return execute(params);
		}
		return new EmptyParameters();

	}

	private Boolean checkRules(int playerID, CommunicationParameters params) {
		Boolean ruletest = false;
		BasicParameters basic = (BasicParameters) params;
		ActionToRuleMap mapobj = basic.getActionToRuleMap();
		if (mapobj.contains(myName)) {
			List<Rules> list = mapobj.getKey(myName);
			if (list != null) {
				Iterator<Rules> ruleItr = list.iterator();
				while (ruleItr.hasNext()) {
					ruletest = ruleItr.next().executeRules((BasicParameters) params, playerID);
					if (ruletest == false) {
						return ruletest;
					}
				}
			}
		}

		return ruletest;
	}

	protected abstract CommunicationParameters execute(CommunicationParameters params);

}
