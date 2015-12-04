
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
public class ModifyWealth extends Outcome {
	private String health;
	Map<String, Object> myParams;
	public ModifyWealth(List<Conditions> conditions, Map<String, Object> params) {
		super(conditions, params);
		myParams = params;
	}

	@Override
	BasicParameters applyOutcome(BasicParameters params, int i) {
	//	params.getEngine().getPlayers().getActivePlayer(i).modifymyWealth(myParams.get(health))
		return params;
	}

}
