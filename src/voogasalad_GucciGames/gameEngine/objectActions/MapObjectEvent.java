package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.LocationParameters;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al class based on template method pattern.
 *
 */
public abstract class MapObjectEvent implements IGamePlayerMapObjectAction{
	private String myName;
	private List<Rules> myRuleList;

	public MapObjectEvent(String actionName) {
		myName = actionName;
		myRuleList = new ArrayList<Rules>();
	}
	
	protected List<Rules> getRuleList(){
		return myRuleList;
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
	
	protected boolean checkNeighborhood(double dx, double dy, double range){
		return (dx <= range) && (dy <=range);
	}
	
	// Must keep final
	public final CommunicationParameters executeAction(CommunicationParameters params, int playerID) {
		if(!(params instanceof LocationParameters)){
			return new EmptyParameters();
		}
		return checkRules(playerID,params) ? executeAction((LocationParameters) params) : new EmptyParameters();
	}
	
	protected abstract CommunicationParameters executeAction(LocationParameters params);
	
	public final CommunicationParameters executeRequest(CommunicationParameters params, int playerID){
		if(!(params instanceof BasicParameters)){
			return new EmptyParameters();
		}
		return checkRules(playerID,params) ? executeRequest((BasicParameters) params) : new EmptyParameters();
	}
	
	protected abstract CommunicationParameters executeRequest(BasicParameters params);

}
