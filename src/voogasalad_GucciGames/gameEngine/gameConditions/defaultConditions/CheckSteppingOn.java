package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

/**
 *
 * @author Sally Al
 *
 */
public class CheckSteppingOn extends Conditions {
	private static final String TARGET_TYPE = "targetType";
	private String myType;

	public CheckSteppingOn(Map<String, Object> condParams) {
		super(condParams);
		if (condParams.containsKey(TARGET_TYPE)) {
			myType = (String) condParams.get(TARGET_TYPE);
		}
	}

	@Override
	public Boolean execute(BasicParameters params, GamePlayerPerson player) {
		MapObject calledMe = params.getCalledMe();
		TargetCoordinateSingle myLocation = (TargetCoordinateSingle) calledMe.getCoordinate();
		List<Integer> ids = params.getEngine().getPlayers().getAllIds();
		for (Integer id : ids) {
			List<MapObject> currPlayerObjects = params.getEngine().getPlayers().getPlayerById(id).getMapObjects();

			for (int i = 0; i < currPlayerObjects.size(); i++) {
				MapObject mo = currPlayerObjects.get(i);
				if (mo.getCoordinate().equals(myLocation) && mo.getName().equals(myType)) {

					return true;
				}
			}
		}
		return false;

	}


}
