package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.MovablePlayerCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class LocationParams extends BasicParameters{
	
	// Input
	private TargetCoordinateSingle myNewLocation;
	private MovablePlayerCharacteristic myMovable;
	
	public LocationParams(BasicParameters params, TargetCoordinateSingle target, MovablePlayerCharacteristic movePerson) {
		super(params);
		// TODO Auto-generated constructor stub
		this.myNewLocation = target;
		this.myMovable = movePerson;
	}
	
	public TargetCoordinateSingle getNewLocation(){
		return this.myNewLocation;
	}
	
	public MovablePlayerCharacteristic getMovePerson(){
		return this.myMovable;
	}
}