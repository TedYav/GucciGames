package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.mapObject.DefaultMapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GameSourceData {
	private ObservableList<MapObjectType> tileTypes;
	private ObservableList<MapObject> tiles;
	private ObservableList<MapObjectType> unitTypes;
	private ObservableList<MapObject> units; 
	private ObservableList<MapObjectType> structureTypes;
	private ObservableList<MapObject> structures;


	private ObservableList<MapObject> onMap;

	GameSourceData() {
		tileTypes = FXCollections.observableArrayList();
		MapObjectType objType = new MapObjectType("fire", "voogasalad_GucciGames/graphics/fire.png");
		MapObjectType objType2 = new MapObjectType("hurricane", "voogasalad_GucciGames/graphics/hurricane.png");
		tileTypes.add(objType);
		tileTypes.add(objType2);
	}

	public void addListener(ListChangeListener o) {
		tileTypes.addListener(o);
		tiles.addListener(o);
		unitTypes.addListener(o);
		units.addListener(o);
		structureTypes.addListener(o);
		structures.addListener(o);
		onMap.addListener(o);



	}

	public void addToMap(MapObject obj) {
		onMap.add(obj);
	}
	public void deleteFromMap(MapObject obj) {
		onMap.remove(obj);
	}
	public void clearMap() {
		onMap.clear();
	}
	public ObservableList<MapObject> getMapObjects(){
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
