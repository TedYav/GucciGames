package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;

public interface IGAEModel {

	public void deleteMapObject(int levelID, DisplayMapObject mapObj);

	public List<DisplayMapObject> getMapObjects(int id);

	public DisplayMapObject addMapObject(int levelID, GridPoint gridpoint, MapObjectType mapObjType);

	public void clearMap(int id);

	public void createCustomTileType(MapObjectType m);

	public void createCustomUnitType(MapObjectType m);

	public void createCustomStructureType(MapObjectType m);

	public ObservableList<MapObjectType> getImmutableTileTypes();

	public ObservableList<MapObjectType> getImmutableUnitTypes();

	public ObservableList<MapObjectType> getImmutableStructureTypes();

	public List<String> getComponents(String location);

	public void setGuiComponents(String location, List<String> components);

	public int addLevel(String name, int width, int height);

	public void saveToXML();

	public void setDefaultOwner(int ownerID);

	public IGameProperties getPropertiesInterface();

	public void addPlayerCharacteristic(int playerID, ObjParamValue param);

	public void setNumberOfPlayers(int n);

	public int getNumberOfPlayers();

	public int getDefaultOwner();

	public void setGameName(String name);
	
	public void deleteMapObjectType(MapObjectType obj);
}
