package voogasalad_GucciGames.gameplayer.windows.mainwindow.map;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Point2D;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public interface MapInterface {

	public void highlightCells(List<TargetCoordinateSingle> list);

	public void clearHighlights();

	public void selectCell(MapCellInterface cell);

	public List<MapCellInterface> getSelectedCells();

	public void clearActiveCells();

	public void redrawFog();

	public void update();

	public void recenter(Point2D center);

	public void update(List<PlayerMapObjectInterface> result);

	public Point2D getCellCoordinate(MapCellInterface cell);

	public MapCellInterface getCell(Point2D coordinate);

	public void recenter(double xPercent, double yPercent);

	public List<Double> getVisibleArea();

	/**
	 * adds a listener to the scroll bar of the map.
	 * 
	 * @param listener
	 */
	public void addHListener(ChangeListener<? super Number> listener);

	/**
	 * Adds a listener on the scroll bar of the map.
	 * 
	 * @param listener
	 */
	public void addVListener(ChangeListener<? super Number> listener);
}
