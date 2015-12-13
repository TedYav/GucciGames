
package voogasalad_GucciGames.gameEngine.objectActions.defaultActions;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectBuilder;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

/**
 *
 * @author Sally Al
 *
 */
public class BuildTower extends MapObjectEvent {

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		return (new MapObjectBuilder()).build("Tower", params);

	}

	@Override
	protected GridCoordinateParameters executeRequest(BasicParameters params) {
		return (new MapObjectBuilder()).request(params);
	}

}
