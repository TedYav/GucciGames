package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MoveMapParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;

public class MoveMapObjectActionTarget implements IMapObjectActionTarget{

	@Override
	public ATargetCoordinate coordinatesToAct(CommunicationParams communication) {
		// TODO Auto-generated method stub
		TargetCoordinateMultiple coordinates = new TargetCoordinateMultiple();
		for(MapObject location: communication.getLocations()){
			if(!location.getObjectType().hasCharacteristic("UnmovableOntoMapObjectCharacteristic")){
				coordinates.addCoordinate(location.getCoordinate());
			}
		}
		return coordinates;
	}

}
