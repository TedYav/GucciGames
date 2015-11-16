
package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRule.oucomes.Outcome;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Conditions {
	private static List<Conditions> RulesInEffect;

	public Conditions(List<GamePlayerPerson> players, Outcome outcome) {
	}

	public abstract void execute();

}
