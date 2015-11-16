package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gameConditions.oucomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class DefaultConditions extends Conditions{

	public DefaultConditions(List<GamePlayerPerson> players, Outcome outcome) {
		super(players, outcome);
	}

}
