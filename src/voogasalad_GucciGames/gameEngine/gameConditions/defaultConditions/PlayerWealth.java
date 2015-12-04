/**
 *
 */
package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerWealth extends Conditions {
	private static final String WEALTH = "wealth";

	public PlayerWealth(Map<String, Object> condParams) {
		super(condParams);
	}

	@Override
	public Boolean execute(GamePlayerPerson player) {
		Boolean flag = false;
		//flag=(player.WealthStatus() == getMyParams().get(WEALTH));
		return flag;
	}

}
