package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class MoveParams extends CommunicationParams{
	
	// Input
	private MapObject myMapObject;
	private TargetCoordinateSingle myNewLocation;
	
	public MoveParams(AllPlayers players, GameMap gameMap, List<MapObject> locations, MapObject target,
			TargetCoordinateSingle newLocation) {
		super(players, gameMap, locations);
		// TODO Auto-generated constructor stub
		this.myMapObject = target;
		this.myNewLocation = newLocation;
	}
	
	public MoveParams(CommunicationParams communication, MapObject target, TargetCoordinateSingle newLocation){
		this(communication.getPlayers(), communication.getGameMap(), communication.getLocations(), target,newLocation);
	}
	
	public MapObject getMapObject(){
		return this.myMapObject;
	}
	
	public TargetCoordinateSingle getNewLocation(){
		return this.myNewLocation;
	}
}
