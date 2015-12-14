package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TypeData {
	private ObservableList<MapObjectType> tileTypes;
	private ObservableList<MapObjectType> unitTypes;
	private ObservableList<MapObjectType> structureTypes;
	
	public TypeData() {
		tileTypes = FXCollections.observableArrayList();
		unitTypes = FXCollections.observableArrayList();
		structureTypes = FXCollections.observableArrayList();
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

	public void addMapObjectType(MapObjectType type) {
		if (type.getType() == ObjectTypes.STRUCTURE)
			structureTypes.add(type);
		if (type.getType() == ObjectTypes.TILE)
			tileTypes.add(type);
		if (type.getType() == ObjectTypes.UNIT)
			unitTypes.add(type);
	}
	
	public void deleteMapObjectType(MapObjectType obj) {
		if (obj.getType() == ObjectTypes.STRUCTURE)
			structureTypes.remove(obj);
		if (obj.getType() == ObjectTypes.UNIT)
			unitTypes.remove(obj);
		if (obj.getType() == ObjectTypes.TILE)
			tileTypes.remove(obj);
	}
	
	public List<MapObjectType> getAllMapObjectTypes() {
		List<MapObjectType> list = new ArrayList<>();
		list.addAll(tileTypes);
		list.addAll(structureTypes);
		list.addAll(unitTypes);
		return list;
	}

}
