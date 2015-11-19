package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParameters;
import voogasalad_GucciGames.gameEngine.gameRules.ActionToRuleManager;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al class based on template method pattern.
 *
 */
public abstract class MapObjectEvent {
	private String myName;
	private List<Rules> myRuleList;

	public MapObjectEvent(String actionName) {
		myName = actionName;
		myRuleList = new ArrayList<Rules>();
	}
	
	protected List<Rules> getRuleList(){
		return myRuleList;
	}

	// must keep executeAction() final
	public final CommunicationParameters executeAction(CommunicationParameters params, int playerID) {
		Boolean check = checkRules(playerID,params);
		if (check) {
			return execute(params);
		}
		return null;
	}


	private Boolean checkRules(int playerID, CommunicationParameters params) {
		Boolean ruletest = true;
		BasicParameters basic = (BasicParameters) params;


			if (myRuleList != null) {
				Iterator<Rules> ruleItr = myRuleList.iterator();
				while (ruleItr.hasNext()) {
					ruletest = ruleItr.next().executeRules((BasicParameters) params);
					if (ruletest == false) {
						return ruletest;
					}
				}
			}

		return ruletest;
	}

	protected abstract CommunicationParameters execute(CommunicationParameters params);

}
