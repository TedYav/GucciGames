package voogasalad_GucciGames.gameEngine.gameRule.defaultConditions.player;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRule.EndGameConditions;
import voogasalad_GucciGames.gameEngine.gameRule.oucomes.Outcome;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerUnitCondition extends PlayerConditions {
	Outcome myOutcome;

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
