
package voogasalad_GucciGames.gameEngine.gameConditions.oucomes;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;

/**
 *
 * @author Sally Al
 *
 */
public class RemovePlayer extends Outcome {

	public RemovePlayer(List<Conditions> conditions) {
		super(conditions);
	}

	@Override
	BasicParameters applyOutcome(BasicParameters params, int id) {

		return params;
	}

}
