package voogasalad_GucciGames.gameplayer.windows.mainwindow.map;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import voogasalad_GucciGames.gameplayer.controller.GameEngineInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.EngineUnit;

public abstract class MapCanvas extends WindowComponent implements MapInterface {

	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.Map");
	private Map<EngineUnit, CellUnit> myUnitMap;
	private Map<Point2D, MapCell> myCellMap;
	
	public MapCanvas(GameScene scene, GameEngineInterface game) {
		super(scene, game);
		initializeMap();
	}

	private void initializeMap() {
		myUnitMap = new HashMap<>();
		myCellMap = new HashMap<>();
		// query width, size, etc
		// myGame.etc etc etc
	}

}
