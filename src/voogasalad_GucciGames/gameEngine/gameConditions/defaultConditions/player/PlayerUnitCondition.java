package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.player;
import java.util.List;

import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;
import voogasalad_GucciGames.gameEngine.gameConditions.oucomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerUnitCondition extends PlayerConditions {
	private Outcome myOutcome;

	public PlayerUnitCondition(List<GamePlayerPerson> players, Outcome outcome) {
		super(players, outcome);
		myOutcome = outcome;
	}

	@Override
	protected void apply(GamePlayerPerson player) {
		if (player.getUnits() != null) {
			if (player.getUnits().size() == 0) {
				player.setStatus(EndGameConditions.LOSE.toString());
				myOutcome.removePlayer(player.getMyPlayerId());
			}
		}
	}
}
