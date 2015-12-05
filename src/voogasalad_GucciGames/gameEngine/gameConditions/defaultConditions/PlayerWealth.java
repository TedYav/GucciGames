/**
 *
 */
package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
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
	public Boolean execute(BasicParameters params,GamePlayerPerson player) {
		return (player.getWealth().status() == (int) getMyParams().get(WEALTH));
	}

}
