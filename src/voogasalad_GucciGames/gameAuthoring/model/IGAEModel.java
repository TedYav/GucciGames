package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Map;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public interface IGAEModel {
    public void addComponent(MapObject mapObj);
    public void deleteComponent(MapObject mapObj);
    public ObservableList<MapObject> getMapObjects();
    public void clearMap();
    
    public void createCustomTileType(Map<String,String> m);
    public void createCustomUnitType(Map<String,String> m);
    
    public ObservableList<MapObjectType> getImmutableTileTypes();
    public ObservableList<MapObjectType> getImmutableUnitTypes();
    public ObservableList<MapObjectType> getImmutableStructureTypes();
    public ObservableList<MapObjectType> getTileTypes();
    public ObservableList<MapObjectType> getUnitTypes();
    public ObservableList<MapObjectType> getStructureTypes();
    
    public void saveToXML();
    
    public void setMapWidth(double x);
    public void setMapHeight(double y);
    
    public void addObserver(ListChangeListener o);
}
