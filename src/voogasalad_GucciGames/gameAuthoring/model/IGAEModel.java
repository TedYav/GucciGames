package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Map;
import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.gameAuthoring.properties.TileProperty;
import voogasalad_GucciGames.gameAuthoring.properties.UnitProperty;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public interface IGAEModel {
    public boolean addComponent(Map<String,String> objParams);
    
    public void deleteComponent(MapObject mapObj);
    public void clearMap();
    
    public void createCustomTileType(TileProperty property) throws InvalidTypeParamsException;
    public void createCustomUnitType(UnitProperty property) throws InvalidTypeParamsException;
    
    public List<MapObjectType> getImmutableTileTypes();
    public List<MapObjectType> getImmutableUnitTypes();
    public List<MapObjectType> getTileTypes();
    public List<MapObjectType> getUnitTypes();
    
    public void saveToXML();
    
    public void setMapWidth(double x);
    public void setMapHeight(double y);
    
    public void addObserver(ListChangeListener o);
}
