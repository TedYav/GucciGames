package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

public abstract class MapObjectEvent implements IGamePlayerMapObjectAction {
	private String myName;
	private List<Rules> myRuleList = new ArrayList<Rules>();
	private List<Outcome> myOutcomes = new ArrayList<Outcome>();

	public MapObjectEvent(String actionName) {
		myName = actionName;
	}

	public MapObjectEvent(String actionName, List<Rules> rules, List<Outcome> outcomes) {
		this(actionName);
		myRuleList.addAll(rules);
		myOutcomes.addAll(outcomes);
	}

	protected List<Rules> getRuleList() {
		return myRuleList;
	}

	private Boolean checkRules(int playerID, CommunicationParameters params) {
		Boolean ruletest = true;
		if (myRuleList != null && !myRuleList.isEmpty()) {
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

	protected boolean checkNeighborhood(double dx, double dy, double range) {
		return (dx <= range) && (dy <= range);
	}

	// Must keep final
	//TODO: loop over outcomes
	public final ChangedParameters executeAction(LocationParameters params, int playerID) {
		return checkRules(playerID, params) ? executeAction(params) : null;
	}

	protected abstract ChangedParameters executeAction(LocationParameters params);

	public final GridCoordinateParameters executeRequest(BasicParameters params, int playerID) {
		return checkRules(playerID, params) ? executeRequest(params) : null;
	}

	protected abstract GridCoordinateParameters executeRequest(BasicParameters params);

}
