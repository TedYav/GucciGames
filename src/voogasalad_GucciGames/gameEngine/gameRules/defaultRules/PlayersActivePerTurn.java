package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al
 *
 */
public class PlayersActivePerTurn extends Rules {

	@Override
	public Boolean executeRules(BasicParameters communicationParams) {
		int currentPlayerID = communicationParams.getEngine().getActivePlayer();
		int currentObjectOwnerID = communicationParams.getCalledMe().getOwnerID();
		return (currentPlayerID == currentObjectOwnerID);
	}

}
