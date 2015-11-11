package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Map;
import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnitType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.tile.TileType;

public interface IGAEModel {
    public void addComponent(Map<String,String> objParams);
    
    public void deleteComponent(MapObject mapObj);
    public void clearMap();
    
    public void createCustomTileType(Map<String,String> m);
    public void createCustomUnitType(Map<String,String> m);
    
    public List<TileType> getImmutableTileTypes();
    public List<GameUnitType> getImmutableUnitTypes();
    
    public void saveToXML();
    
    public void setMapWidth(double x);
    public void setMapHeight(double y);
    
    public void addObserver(ListChangeListener o);
}
