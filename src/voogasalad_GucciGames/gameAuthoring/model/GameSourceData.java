package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.TileCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.DefaultMapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GameSourceData {
	private ObservableList<MapObjectType> tileTypes;
	private ObservableList<MapObjectType> unitTypes;
	private ObservableList<MapObjectType> structureTypes;
	private List<MapObject> onMap;

	GameSourceData() {
		tileTypes = FXCollections.observableArrayList();
		// Hard coded for testing purposes:
		MapObjectType objType = new MapObjectType("duvall", "player/images/dummytexture.jpg");
		MapObjectType objType2 = new MapObjectType("student", "player/images/dummytexture2.jpg");
		objType.addCharacteristic("TileCharacteristic", new TileCharacteristic());
		objType.addCharacteristic("TileCharacteristic", new TileCharacteristic());
		tileTypes.add(objType);
		tileTypes.add(objType2);
		
		unitTypes = FXCollections.observableArrayList();
		MapObjectType unitType1 = new MapObjectType("duvall", "player/images/duvall.png");
		MapObjectType unitType2 = new MapObjectType("student", "player/images/smile.png");
		MapObjectType unitType3 = new MapObjectType("student", "player/images/mario.png");
		unitTypes.add(unitType1);
		unitTypes.add(unitType2);
		unitTypes.add(unitType3);
	}

	public void addToMap(MapObject obj) {
		onMap.add(obj);
	}
//	public void deleteFromMap(MapObject obj) {
//		onMap.remove(obj);
//	}
	public void clearMap() {
		onMap.clear();
	}
	public List<MapObject> getMapObjects(){
		return onMap;
	}
	public void addTileType(MapObjectType type) {
		tileTypes.add(type);
	}
	public void addUnitType(MapObjectType type) {
		unitTypes.add(type);
	}
	public void addStructureType(MapObjectType type) {
		structureTypes.add(type);
	}
	public ObservableList<MapObjectType> getImmutableTileTypes() {
		return FXCollections.unmodifiableObservableList(tileTypes);
	}
	public ObservableList<MapObjectType> getImmutableUnitTypes() {
		return FXCollections.unmodifiableObservableList(unitTypes);
	}
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return FXCollections.unmodifiableObservableList(structureTypes);
	}
}
