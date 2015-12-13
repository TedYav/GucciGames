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

	public MapObjectEvent() {
	}

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

	public void addRule(Rules rule) {
		myRuleList.add(rule);
	}

	public void addOutcome(Outcome outcome) {
		myOutcomes.add(outcome);
	}

	public void addActionName(String name) {
		myName = name;
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

	/*
	 * Execution of Attack
	 */
	public final ChangedParameters executeAction(LocationParameters params, int playerID) {
		return checkRules(playerID, params) ? executeOutcome(params, executeAction(params)) : null;
	}

	protected abstract ChangedParameters executeAction(LocationParameters params);

	private ChangedParameters executeOutcome(BasicParameters basic, ChangedParameters changed) {
		for (Outcome outcome : this.myOutcomes) {
			changed = outcome.executeOutcome(basic, changed);
		}
		return changed;
	}

	/*
	 * Execution of request.
	 */
	public final GridCoordinateParameters executeRequest(BasicParameters params, int playerID) {
		return checkRules(playerID, params) ? executeRequest(params) : null;
	}

	protected abstract GridCoordinateParameters executeRequest(BasicParameters params);

	public String getName() {
		return this.myName;
	}
}
