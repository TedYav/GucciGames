package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MoveParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class MoveAction implements IMapObjectAction{

	@Override
	public CommunicationParams action(CommunicationParams communication) {
		// TODO Auto-generated method stub
		MoveParams move = (MoveParams) communication;
		MapObject moving = move.getMapObject();
		TargetCoordinateSingle target = move.getNewLocation();
		moving.setCoordinate(target);
		return new EmptyParams();
	}

}
