package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MoveParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class MoveAction implements IMapObjectAction{


	@Override
	public CommunicationParams action(CommunicationParams communication, ATargetCoordinate targetCoords) {
		// TODO Auto-generated method stub
		
		//TODO: Decide on what kind this action supports for. for now, a cheezy if tree
		
		if(targetCoords instanceof TargetCoordinateSingle){
		
		MapObject moving = communication.getMapObject();
		
		TargetCoordinateSingle target = (TargetCoordinateSingle) targetCoords;
		moving.setCoordinate(target);
		
		}
		
		
		return new EmptyParams();
		
		
	}

}
