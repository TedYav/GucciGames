
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Conditions {
	private Map<String, Object> myParams;

	public Conditions(Map<String,Object> condParams) {
		myParams = condParams;
	}

	public abstract Boolean execute(BasicParameters params, GamePlayerPerson player);

	protected Map<String,Object> getMyParams() {
		return myParams;
	}

}
