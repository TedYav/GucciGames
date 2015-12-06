
package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerWealthChar;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerWealth extends Conditions {
	private static final String WEALTH = "wealth";
	private int myValue =0;

	public PlayerWealth(int value) {
		myValue = value;
	}

	@Override
	public Boolean execute(BasicParameters params,GamePlayerPerson player) {
		PlayerWealthChar playerWealth = (PlayerWealthChar)player.getCharacteristics(WEALTH);
		return (playerWealth.getWealth() == myValue);
	}

}
