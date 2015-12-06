
package voogasalad_GucciGames.gameEngine.objectActions.defaultActions;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

/**
 *
 * @author Sally Al
 *
 */
public class PowerfulAttack extends Attack {

	public PowerfulAttack(String actionName) {
		super(actionName);
	}

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		System.out.println("Attack Action");
		MapObject calledMe = params.getCalledMe();
		return null;
	}

}
