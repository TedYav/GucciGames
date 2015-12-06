
package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerWealthChar;

/**
 *
 * @author Sally Al
 *
 */
public class ModifyWealth extends Outcome {
	private static final String WEALTH = "wealth";

	public ModifyWealth(List<Conditions> conditions, OutcomeParams conditionParams) {
		super(conditions, conditionParams);
	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params, ChangedParameters changedParams, int playerID) {
		int delta = (int) this.getMyParams().getArgumentValue(WEALTH);
		if (params.getEngine().getPlayers().getActivePlayer(playerID).hasCharerctristic(WEALTH)) {
			PlayerWealthChar playerWealth = (PlayerWealthChar) params.getEngine().getPlayers().getActivePlayer(playerID)
					.getMyCharacteristics(WEALTH);
			playerWealth.modifyWealth(delta);
			changedParams.addPlayer(playerID);

		}

		return changedParams;
	}

}
