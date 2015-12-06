package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.model.DisplayMapObject;
import voogasalad_GucciGames.gameAuthoring.model.LevelData;
import voogasalad_GucciGames.gameAuthoring.model.MapData;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import voogasalad_GucciGames.gameAuthoring.model.TypeData;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GuiData;
import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class GameInfoFactory {

	public GameInfo create(TypeData typeData, 
			LevelData levelData, GuiData guiData) {
		Map<Integer, MapData> map = levelData.getMap();
		List<GameLevelEngine> levels = new ArrayList<GameLevelEngine>();
		for (int levelid: map.keySet()) {
			levels.add(makeLevelEngine(typeData.getMapOfPlayers(), typeData, map.get(levelid)));
		}
		//GameInfo game = new GameInfo(levels.get(0));
		return null;
		
	}

	private GameLevelEngine makeLevelEngine(Map<Integer, GamePlayerPerson> mapOfPlayers, 
			TypeData typeData, MapData mapData) {
		HashMap copyMapOfPlayers = new HashMap<>(mapOfPlayers);
		for (DisplayMapObject obj: mapData.getMapObjects()) {
			MapObjectType type = obj.getType();
			List<AMapObjectCharacteristic> characteristics = type.getCharacteristics();
			List<MapObjectEvent> events = type.getEvents();
			TargetCoordinateSingle coord = new TargetCoordinateSingle(obj.getCoordinate().getX(),
					obj.getCoordinate().getY());
			MapObject mapObject = new MapObject(coord, obj.getOwnerID(), 
					type.getLayer(), type.getName(), type.getImagePath());
			characteristics.stream().forEach(a -> {
				mapObject.addCharacteristic(a.getClass().getSimpleName(), a);
			});
			events.stream().forEach(a -> {
				mapObject.addEvent(a.getClass().getSimpleName(), a);
			});
			copyMapOfPlayers.get(obj.getOwnerID());
			
		}
		AllPlayers allplayers = new AllPlayers(copyMapOfPlayers);
		return new GameLevelEngine(allplayers);
		
	}

}
