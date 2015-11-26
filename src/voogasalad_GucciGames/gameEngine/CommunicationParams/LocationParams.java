package voogasalad_GucciGames.gameEngine.CommunicationParams;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class LocationParams extends BasicParameters{
	
	// Input
	private TargetCoordinateSingle myNewLocation;
	//private MovablePlayerCharacteristic myMovable;
	
	public LocationParams(BasicParameters params, MapObject obj, TargetCoordinateSingle target) {
		super(params, obj);
		// TODO Auto-generated constructor stub
		this.myNewLocation = target;
		//this.myMovable = movePerson;
	}
	
	public TargetCoordinateSingle getNewLocation(){
		return this.myNewLocation;
	}
	
	//public MovablePlayerCharacteristic getMovePerson(){
	//	return this.myMovable;
	//}
	
}
