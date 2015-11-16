package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;

public class MoveMapParams extends CommunicationParams{
	
	private TargetCoordinateMultiple myTargetCoordinateMultiple;
	
	public MoveMapParams(AllPlayers players, GameMap gameMap, List<MapObject> locations,
			MapObject currentActiveMapObject,TargetCoordinateMultiple target) {
		super(players, gameMap, locations, currentActiveMapObject);
		// TODO Auto-generated constructor stub
		this.myTargetCoordinateMultiple = target;
	}
	
	public MoveMapParams(CommunicationParams communication, TargetCoordinateMultiple target){
		this(communication.getPlayers(), communication.getGameMap(), communication.getLocations(),
				communication.getMapObject(),target);
	}
	
	public TargetCoordinateMultiple getTargetCoordinateMultiple(){
		return this.myTargetCoordinateMultiple;
	}

}
