package voogasalad_GucciGames.gameAuthoring.model;

import java.util.Collections;
import java.util.List;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnit;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnitType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameEngine.tile.Tile;
import voogasalad_GucciGames.gameEngine.tile.TileType;

public class GameSourceData {
    private ObservableList<TileType> tileTypes;
    private ObservableList<Tile> tiles;
    private ObservableList<GameUnitType> unitTypes;
    private ObservableList<GameUnit> units;
    
    private ObservableList<MapObject> onMap;
    
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
    public void addTileType(TileType type) {
        tileTypes.add(type);
    }
    public void addUnitType(GameUnitType type) {
        unitTypes.add(type);
    }
    public List<TileType> getImmutableTileTypes() {
        return Collections.unmodifiableList(tileTypes);
    }
    public List<GameUnitType> getImmutableUnitTypes() {
        return Collections.unmodifiableList(unitTypes);
    }
}
