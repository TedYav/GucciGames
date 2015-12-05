
package voogasalad_GucciGames.gameEngine.objectActions.defaultActions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

/**
 *
 * @author Sally Al
 *
 */
public class PowerfulAttack extends MapObjectEvent{


	public PowerfulAttack(String actionName, List<Rules> rules, List<Outcome> outcomes) {
		super(actionName, rules, outcomes);
	}

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		return null;
	}
	@Override
	protected GridCoordinateParameters executeRequest(BasicParameters params) {
		return null;
	}

}
