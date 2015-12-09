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
			LevelData levelData, GuiData guiData, List<MapObjectType> mapObjectTypeList, String name) {
		
		GameInfo game = new GameInfo(name);
		
		game.setGuiData(guiData);
		
				
		Map<Integer, MapData> levelMap = levelData.getMap();
		for (int levelid: levelMap.keySet()) {
			MapData level = levelMap.get(levelid);
			game.getGameEngine().addLevel(level.getName(),makeLevel(typeData.getMapOfPlayers(), typeData, level));
		}
		
		Map<String, MapObject> allMapObjects = makeMapObjects(mapObjectTypeList);
		
		game.getGameEngine().addMapObjects(allMapObjects);
		
		return game;
	}
	
	
	private Map<String, MapObject> makeMapObjects (List<MapObjectType> mapObjectTypeList) {
	       Map<String, MapObject> mapObjects = new HashMap<>();
	       
	       mapObjectTypeList.stream().forEach(type -> {
	           
	               List<AMapObjectCharacteristic> characteristics = type.getCharacteristics();
	               List<MapObjectEvent> events = type.getEvents();
	               //                TargetCoordinateSingle coord = new TargetCoordinateSingle(obj.getCoordinate().getX(),
	               //                                obj.getCoordinate().getY());
	               MapObject mapObject = new MapObject(null, -1, 
	                                                   type.getLayer(), type.getName(), type.getImagePath());            
	               characteristics.stream().forEach(a -> {
	                   mapObject.addCharacteristic(a.getClass().getSimpleName(), a);
	               });
	               events.stream().forEach(a -> {
	                   mapObject.addEvent(a.getClass().getSimpleName(), a);
	               }); 
	               mapObjects.put(type.getName(), mapObject);

	       });
	       
	       
	       return mapObjects;
	}

	private GameLevelEngine makeLevel(Map<Integer, GamePlayerPerson> mapOfPlayers, 
			TypeData typeData, MapData mapData) {
		Map<Integer, GamePlayerPerson> copyMapOfPlayers = new HashMap<>(mapOfPlayers);
		for (DisplayMapObject obj: mapData.getMapObjects()) {
			MapObjectType type = obj.getType();
			List<AMapObjectCharacteristic> characteristics = type.getCharacteristics();
			List<MapObjectEvent> events = type.getEvents();
			TargetCoordinateSingle coord = new TargetCoordinateSingle(obj.getCoordinate().getX(),
					obj.getCoordinate().getY());
			MapObject mapObject = new MapObject(coord, obj.getOwnerID(), 
					type.getLayer(), type.getName(), type.getImagePath());
			System.out.println("loading characteristics: " + characteristics.size()  + " " + characteristics);
			System.out.println("loading events: " + events.size() + "  " + events);
			characteristics.stream().forEach(a -> {
				mapObject.addCharacteristic(a.getClass().getSimpleName(), a);
			});
			events.stream().forEach(a -> {
				mapObject.addEvent(a.getClass().getSimpleName(), a);
			});
			copyMapOfPlayers.get(obj.getOwnerID()).addMapObject(mapObject);
			
		}
		AllPlayers allplayers = new AllPlayers(copyMapOfPlayers);
		GameLevelEngine level = new GameLevelEngine(allplayers);
		
		level.setMapHeight(mapData.getHeight());
		level.setMapWidth(mapData.getWidth());
		level.setMyChoosability(true);
		return level;
		
	}

}
