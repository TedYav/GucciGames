
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gameConditions.oucomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Conditions {
	private Outcome myOutcome;

	public Conditions(List<GamePlayerPerson> players, Outcome outcome) {
		myOutcome=outcome;
	}

	public abstract void execute();

	protected Outcome getMyOutcome() {
		return myOutcome;
	}



}
