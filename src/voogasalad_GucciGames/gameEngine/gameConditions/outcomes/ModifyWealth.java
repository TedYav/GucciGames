
package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerWealthChar;

/**
 *
 * @author Sally Al
 *
 */
public class ModifyWealth extends Outcome {
	private static final String WEALTH = "PlayerWealthChar";
	private int delta = 0;
	
	public ModifyWealth() {
		
	}

	public ModifyWealth(String affectedPlayers, int value) {
		super(affectedPlayers);
		delta = value;
	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params, ChangedParameters changedParams, int playerID) {
		if (params.getEngine().getPlayers().getActivePlayer(playerID).hasCharacteristic(WEALTH)) {
			PlayerWealthChar playerWealth = (PlayerWealthChar) params.getEngine().getPlayers().getActivePlayer(playerID)
					.getCharacteristics(WEALTH);
			playerWealth.modifyWealth(delta);
			changedParams.addPlayer(playerID);

		}

		return changedParams;
	}

}
