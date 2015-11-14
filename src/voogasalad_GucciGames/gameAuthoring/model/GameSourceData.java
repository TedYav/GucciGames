package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GameSourceData {
  private ObservableList<MapObjectType> tileTypes;
    private ObservableList<MapObject> tiles;
    private ObservableList<MapObjectType> unitTypes;
    private ObservableList<MapObject> units; 
    
    public void addListener(ListChangeListener o) {
        tileTypes.addListener(o);
        tiles.addListener(o);
        unitTypes.addListener(o);
        units.addListener(o);
    } 
}
