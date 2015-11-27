/**
 *
 */
package voogasalad_GucciGames.gameEngine.gameConditions.oucomes;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.AllPlayersCommunicationParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;

/**
 *
 * @author Sally Al
 *
 */
public class RemovePlayer extends Outcome{


	public RemovePlayer(List<Conditions> conditions) {
		super(conditions);
	}
	@Override
	void applyOutcome(AllPlayersCommunicationParameters params, int id) {
		params.removePlayerByID(id);

	}

}
