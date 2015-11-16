package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GameSourceData {
  private ObservableList<MapObjectType> tileTypes;
    private ObservableList<MapObject> tiles;
    private ObservableList<MapObjectType> unitTypes;
    private ObservableList<MapObject> units; 
    
    private ObservableList<MapObject> onMap;
    
    GameSourceData(){
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
    public void addTileType(MapObjectType type) {
        tileTypes.add(type);
    }
    public void addUnitType(MapObjectType type) {
        unitTypes.add(type);
    }
    public List<MapObjectType> getImmutableTileTypes() {
        return Collections.unmodifiableList(tileTypes);
    }
    public List<MapObjectType> getImmutableUnitTypes() {
        return Collections.unmodifiableList(unitTypes);
    }
}
