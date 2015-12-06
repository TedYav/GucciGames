
package voogasalad_GucciGames.gameEngine.objectActions.defaultActions;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

/**
 *
 * @author Sally Al
 *
 */
public class PowerfulAttack extends Attack {


	public PowerfulAttack() {
	}

	public PowerfulAttack(String actionName) {
		super(actionName);
	}

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		System.out.println("Attack Action");
		MapObject calledMe = params.getCalledMe();
		int newOwnerId = calledMe.getOwnerID();
		List<Integer> ids = extractAllPlayersExceptNutral(params);

		TargetCoordinateSingle target = params.getNewLocation();
		ChangedParameters parameters = new ChangedParameters();
		List<MapObject> objectsToTransfer = new ArrayList<MapObject>();
		for (Integer id : ids) {
			List<MapObject> currPlayerObjects = params.getEngine().getPlayers().getPlayerById(id).getMapObjects();

			for (int i = 0; i < currPlayerObjects.size(); i++) {
				MapObject mo = currPlayerObjects.get(i);
				if (mo.getCoordinate().equals(target)) {
					params.getEngine().getPlayers().getPlayerById(id).getMapObjects().remove(i);
					objectsToTransfer.add(mo);
					parameters.addUnit(mo);
					break;
				}
			}
			for (int i = 0; i < currPlayerObjects.size(); i++) {
				params.getEngine().getPlayers().getPlayerById(newOwnerId).getMapObjects().add(currPlayerObjects.get(i));
			}
		}
		return parameters;
	}

}
