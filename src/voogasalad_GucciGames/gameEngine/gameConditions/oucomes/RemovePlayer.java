/**
 *
 */
package voogasalad_GucciGames.gameEngine.gameConditions.oucomes;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
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
	void applyOutcome(BasicParameters params, int id) {
		// TODO Auto-generated method stub
		params.getEngine().getPlayers().removePlayer(id);
	}

}
