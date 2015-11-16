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
	
	public MoveParams(AllPlayers players, GameMap gameMap, List<MapObject> locations, MapObject currentActiveMapObject,MapObject target,
			TargetCoordinateSingle newLocation) {
		super(players, gameMap, locations,currentActiveMapObject);
		// TODO Auto-generated constructor stub
		this.myMapObject = target;
		this.myNewLocation = newLocation;
	}
	
	public MoveParams(CommunicationParams communication, MapObject currentActiveMapObject,MapObject target, TargetCoordinateSingle newLocation){
		this(communication.getPlayers(), communication.getGameMap(), communication.getLocations(), 
				currentActiveMapObject,target,newLocation);
	}
	
	public MapObject getMapObject(){
		return this.myMapObject;
	}
	
	public TargetCoordinateSingle getNewLocation(){
		return this.myNewLocation;
	}
}
