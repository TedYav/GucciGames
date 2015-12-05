
package voogasalad_GucciGames.gameEngine.gameConditions.oucomes;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;

/**
 *
 * @author Sally Al
 *
 */
public class RemovePlayer extends Outcome {

	public RemovePlayer(List<Conditions> conditions, Map<String, Object> params) {
		super(conditions, params);
	}

	@Override
	BasicParameters applyOutcome(BasicParameters params, int id) {
		params.getEngine().getPlayers().removePlayer(id);
		return params;
	}

}
