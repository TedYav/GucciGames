
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
public class EndGame extends Outcome {

	public EndGame(List<Conditions> conditions, OutcomeParams conditionParams) {
		super(conditions, conditionParams);
	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params,ChangedParameters changedParams, int i) {
		params.getEngine().setGameWon(true);
		System.out.println("setgame="+params.getEngine().isGameWon());
		return changedParams;
	}

}
