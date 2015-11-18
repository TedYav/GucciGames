package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.player;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerUnitCondition extends PlayerConditions {


	public PlayerUnitCondition(List<GamePlayerPerson> players, BasicParameters params) {
		super(players, params);

	}

	@Override
	protected void apply(GamePlayerPerson player) {
		if (player.getUnits() != null) {
			if (player.getUnits().size() == 0) {
				player.setStatus(EndGameConditions.LOSE.toString());
				getMyOutcome().removePlayer(player.getMyPlayerId());
			}
		}
	}
}
