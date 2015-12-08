
package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;

/**
 *
 * @author Sally Al
 *
 */
public class RemovePlayer extends Outcome {

	public RemovePlayer(String affectedPlayers) {
		super(affectedPlayers);
	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params, ChangedParameters changedParams, int id) {
		if (params.getLevelEngine().getPlayers().getAllIds().contains(id)) {
			params.getLevelEngine().getPlayers().removePlayer(id);
			changedParams.addPlayer(id);
		}
		return changedParams;
	}

}
