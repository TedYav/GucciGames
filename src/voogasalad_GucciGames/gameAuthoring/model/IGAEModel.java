package voogasalad_GucciGames.gameAuthoring.model;

import java.io.File;
import java.util.List;
import java.util.Map;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public interface IGAEModel {

    public void deleteComponent(MapObject mapObj);
    public List<MapObject> getMapObjects();
    public MapObject addObject(GridPoint gridpoint, MapObjectType mapObjType, int ownerID);
    public void clearMap();
    
    public void createCustomTileType(Map<String,String> m);
    public void createCustomUnitType(Map<String,String> m);
    
    public ObservableList<MapObjectType> getImmutableTileTypes();
    public ObservableList<MapObjectType> getImmutableUnitTypes();
    public ObservableList<MapObjectType> getImmutableStructureTypes();

    
    public void saveToXML(File file);
    
    public void setMapWidth(double x);
    public void setMapHeight(double y);
	public void changeOwner(MapObject mapObject, int playerID);
    
}
