package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnit;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnitType;
import voogasalad_GucciGames.gameEngine.tile.Tile;
import voogasalad_GucciGames.gameEngine.tile.TileType;

public class GameSourceData {
    private List<TileType> tileTypes;
    private List<Tile> tiles;
    private List<GameUnitType> unitTypes;
    private List<GameUnit> units;
}
