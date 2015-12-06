
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
public class EndLevel extends Outcome {
	private static final String NEXT_LEVEL="nextLevel";
	OutcomeParams myParams;
	public EndLevel(List<Conditions> conditions, OutcomeParams conditionParams) {
		super(conditions, conditionParams);
		myParams = conditionParams;
	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params,ChangedParameters changedParams, int i) {
		params.getEngine().setEndLevel(true);
	//	params.getEngine().changeLevel(myParams.getArgumentValue(NEXT_LEVEL));
		System.out.println("setgame="+params.getEngine().hasLevelEnded());
		return changedParams;
	}

}
