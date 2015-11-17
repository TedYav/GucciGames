package voogasalad_GucciGames.gameplayer.windows.mainwindow.map;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public interface MapInterface {

	public void highlightCell(Point2D target);
	public void clearHighlights();
	
	public void activateCell(MapCellInterface cell);
	public List<MapCellInterface> getActiveCells();
	public void clearActiveCells();
	
	public void redrawFog();
	public void update();
	
	public void recenter(Point2D center);
}
