package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class CheckPlayerObjects extends Conditions {

	public CheckPlayerObjects(Map<String,Object> condParams) {
		super(condParams);

	}

	@Override
	public Boolean execute(GamePlayerPerson player) {
		if (player.getMapObjects().size() == 0)
			return true;
		else
			return false;
	}

}
