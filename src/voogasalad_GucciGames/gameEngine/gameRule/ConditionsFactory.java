package voogasalad_GucciGames.gameEngine.gameRule;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRule.oucomes.Outcome;

/**
 *
 * @author Sally Al
 *
 */
public class ConditionsFactory {
	AllPlayers myPlayers;
	Outcome myOutcomes;

	public ConditionsFactory(AllPlayers players) {
		myPlayers = players;
		myOutcomes = new Outcome(players);
	}

}
