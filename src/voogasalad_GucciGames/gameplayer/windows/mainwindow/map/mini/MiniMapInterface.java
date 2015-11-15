package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini;

import java.util.Map;

import javafx.geometry.Point2D;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;

public interface MiniMapInterface {

	public void recenter(MapCell cell);
	public void recenter(Point2D coordinate);
	void initialize(Map<Point2D, String> images);
	
}
