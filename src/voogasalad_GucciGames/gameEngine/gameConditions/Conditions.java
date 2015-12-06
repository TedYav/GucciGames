
package voogasalad_GucciGames.gameEngine.gameConditions;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Conditions {

	public Conditions() {
	}

	public Conditions(String name, String value) {
	}

	public Conditions(String name, int value) {
	}

	public abstract Boolean execute(BasicParameters params, GamePlayerPerson player);

}
