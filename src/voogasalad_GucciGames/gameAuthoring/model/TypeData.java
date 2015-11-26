package voogasalad_GucciGames.gameAuthoring.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.TileCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class TypeData {
	private ObservableList<MapObject> tileTypes;
	private ObservableList<MapObject> unitTypes;
	private ObservableList<MapObject> structureTypes;
//	private List<MapObject> onMap;

	public TypeData() {
		tileTypes = FXCollections.observableArrayList();
		// Hard coded for testing purposes:
//		MapObjectType objType = new MapObjectType("Grass", "player/images/dummytexture.jpg");
//		MapObjectType objType2 = new MapObjectType("Water", "player/images/dummytexture2.jpg");
	
		MapObject objType = new MapObject("AllTiles", "player/images/allTiles.jpg", 0, 0, 100.0, 100.0);
		MapObject objType2 = new MapObject("AllTiles2", "player/images/allTiles.jpg", 1, 0, 100.0, 100.0);
		
		objType.addCharacteristic("TileCharacteristic", new TileCharacteristic());
		objType2.addCharacteristic("TileCharacteristic", new TileCharacteristic());
		tileTypes.add(objType);
		tileTypes.add(objType2);
//		objType.addDefaultCharacteristic("TileCharacteristic", new TileCharacteristic());
//		objType2.addDefaultCharacteristic("TileCharacteristic", new TileCharacteristic());
//		tileTypes.add(objType);
//		tileTypes.add(objType2);
		
		unitTypes = FXCollections.observableArrayList();
//		MapObjectType unitType1 = new MapObjectType("duvall", "player/images/duvall.png");
//		MapObjectType unitType2 = new MapObjectType("student", "player/images/smile.png");
//		MapObjectType unitType3 = new MapObjectType("student", "player/images/mario.png");
//		unitTypes.add(unitType1);
//		unitTypes.add(unitType2);
//		unitTypes.add(unitType3);
	}

//	public void addToMap(MapObject obj) {
//		onMap.add(obj);
//	}
//	public void deleteFromMap(MapObject obj) {
//		onMap.remove(obj);
//	}
//	public void clearMap() {
//		onMap.clear();
//	}
//	public List<MapObject> getMapObjects(){
//		return onMap;
//	}
	public void addTileType(MapObject type) {
		tileTypes.add(type);
	}
	public void addUnitType(MapObject type) {
		unitTypes.add(type);
	}
	public void addStructureType(MapObject type) {
		structureTypes.add(type);
	}
	public ObservableList<MapObject> getImmutableTileTypes() {
		return FXCollections.unmodifiableObservableList(tileTypes);
	}
	public ObservableList<MapObject> getImmutableUnitTypes() {
		return FXCollections.unmodifiableObservableList(unitTypes);
	}
	public ObservableList<MapObject> getImmutableStructureTypes() {
		return FXCollections.unmodifiableObservableList(structureTypes);
	}
}
