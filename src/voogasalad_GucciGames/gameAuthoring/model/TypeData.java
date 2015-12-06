package voogasalad_GucciGames.gameAuthoring.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.TileCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class TypeData {
	private ObservableList<MapObjectType> tileTypes;
	private ObservableList<MapObjectType> unitTypes;
	private ObservableList<MapObjectType> structureTypes;
//	private List<MapObject> onMap;

	public TypeData() {
		tileTypes = FXCollections.observableArrayList();
	
		MapObjectType objType = new MapObjectType("AllTiles", "player/images/allTiles.jpg", 0);
		MapObjectType objType2 = new MapObjectType("AllTiles2", "player/images/allTiles.jpg", 0);

		
		tileTypes.add(objType);
		tileTypes.add(objType2);
		
		unitTypes = FXCollections.observableArrayList();
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
