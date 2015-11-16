package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class CommunicationParams {
	
	// Classes which extend this will be used to share information between the front and back end
	private AllPlayers myPlayers;
	private GameMap myGameMap;
	private List<MapObject> myLocations;
	private MapObject myCurrentActiveMapObject;
	
	
	
	public CommunicationParams(AllPlayers players, GameMap gameMap, List<MapObject> locations, MapObject currentActiveMapObject){ 
		this.myPlayers = players;
		this.myGameMap = gameMap;
		this.myLocations = locations;
		this.myCurrentActiveMapObject = currentActiveMapObject;
	}
	
	public CommunicationParams(CommunicationParams params){
		this.myPlayers = params.myPlayers;
		this.myGameMap = params.myGameMap;
		this.myLocations = params.getLocations();
	}

	public AllPlayers getPlayers() {
		return myPlayers;
	}

	public GameMap getGameMap() {
		return myGameMap;
	}

	public List<MapObject> getLocations() {
		return myLocations;
	}

	public MapObject getMapObject() {
		return myCurrentActiveMapObject;
	}
	
}
