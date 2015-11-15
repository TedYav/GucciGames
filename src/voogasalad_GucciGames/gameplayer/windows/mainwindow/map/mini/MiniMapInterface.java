package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini;

import javafx.geometry.Point2D;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;

public interface MiniMapInterface {

	public void recenter(MapCell cell);
	public void recenter(Point2D coordinate);
	
}
