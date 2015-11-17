package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class MoveParams extends CommunicationParams{
	
	// Input
	private TargetCoordinateSingle myNewLocation;
	
	public MoveParams(CommunicationParams params, TargetCoordinateSingle target) {
		super(params);
		// TODO Auto-generated constructor stub
		this.myNewLocation = target;
	}
	
	public TargetCoordinateSingle getNewLocation(){
		return this.myNewLocation;
	}
}
