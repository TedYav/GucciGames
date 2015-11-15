package voogasalad_GucciGames.gameEngine.gameRule.defaultConditions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRule.Conditions;
import voogasalad_GucciGames.gameEngine.gameRule.oucomes.Outcome;

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
