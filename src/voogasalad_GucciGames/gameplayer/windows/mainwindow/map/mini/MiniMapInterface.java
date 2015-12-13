package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini;

import java.util.Map;

import javafx.geometry.Point2D;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public interface MiniMapInterface {

	public void recenter(MapCellInterface cell);

	public void recenter(Point2D coordinate);

	void initialize(Map<Point2D, String> images);

}
