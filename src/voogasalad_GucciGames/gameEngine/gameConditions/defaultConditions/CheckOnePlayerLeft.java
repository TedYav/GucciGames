package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class CheckOnePlayerLeft extends Conditions {


	@Override
	public Boolean execute(BasicParameters params, GamePlayerPerson player) {
		return (params.getEngine().getPlayers().getAllIds().size() == 2);

	}

}
