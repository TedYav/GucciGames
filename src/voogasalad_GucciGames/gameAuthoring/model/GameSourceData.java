package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnit;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnitType;
import voogasalad_GucciGames.gameEngine.tile.Tile;
import voogasalad_GucciGames.gameEngine.tile.TileType;

public class GameSourceData {
    private ObservableList<TileType> tileTypes;
    private ObservableList<Tile> tiles;
    private ObservableList<GameUnitType> unitTypes;
    private ObservableList<GameUnit> units;
    
    public void addListener(ListChangeListener o) {
        tileTypes.addListener(o);
        tiles.addListener(o);
        unitTypes.addListener(o);
        units.addListener(o);
    }
}
