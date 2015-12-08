package voogasalad_GucciGames.gameEngine.objectActions;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;

public class BuildSoldierEvent extends MapObjectEvent{
	

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		// TODO Auto-generated method stub
		return (new Builder()).build("Soldier", params);
	}

	@Override
	protected GridCoordinateParameters executeRequest(BasicParameters params) {
		// TODO Auto-generated method stub
		return (new Builder()).request(params);
	}

}
