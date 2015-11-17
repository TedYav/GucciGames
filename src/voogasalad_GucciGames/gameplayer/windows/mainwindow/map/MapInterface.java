package voogasalad_GucciGames.gameplayer.windows.mainwindow.map;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.dummy.TargetCoordinate;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public interface MapInterface {

	public void highlightCells(List<TargetCoordinate> targets);
	public void clearHighlights();
	
	public void selectCell(MapCellInterface cell);
	public List<MapCellInterface> getSelectedCells();
	public void clearActiveCells();
	
	public void redrawFog();
	public void update();
	
	public void recenter(Point2D center);
	public void update(List<PlayerMapObjectInterface> result);
}
