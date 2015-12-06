package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class CheckPlayerObjects extends Conditions {

	public CheckPlayerObjects() {
	}

	@Override
	public Boolean execute(BasicParameters params,GamePlayerPerson player) {
		if (player.getMapObjects().size() == 0)
			return true;
		else
			return false;
	}

}
