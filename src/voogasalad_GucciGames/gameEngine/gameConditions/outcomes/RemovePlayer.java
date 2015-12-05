
package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;

/**
 *
 * @author Sally Al
 *
 */
public class RemovePlayer extends Outcome {

	public RemovePlayer(List<Conditions> conditions, OutcomeParams conditionParams) {
		super(conditions, conditionParams);
	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params, ChangedParameters changedParams, int id) {
		if (params.getEngine().getPlayers().getAllIds().contains(id)) {
			params.getEngine().getPlayers().removePlayer(id);
			changedParams.addPlayer(id);
		}
		return changedParams;
	}

}
