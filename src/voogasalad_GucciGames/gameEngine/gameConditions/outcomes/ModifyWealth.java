
package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;

/**
 *
 * @author Sally Al
 *
 */
public class ModifyWealth extends Outcome {
	private static final String WEALTH = "wealth";
	Map<String, Object> myParams;

	public ModifyWealth(List<Conditions> conditions, Map<String, Object> params) {
		super(conditions, params);
		myParams = params;
	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params,ChangedParameters changedParams, int i) {
		int delta = (int) myParams.get(WEALTH);
		params.getEngine().getPlayers().getActivePlayer(i).getWealth().modifyWealth(delta);
		changedParams.addPlayer(i);
		return changedParams;
	}

}
