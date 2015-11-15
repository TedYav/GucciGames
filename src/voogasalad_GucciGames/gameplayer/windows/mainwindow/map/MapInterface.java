package voogasalad_GucciGames.gameplayer.windows.mainwindow.map;

import java.util.List;

import com.sun.javafx.scene.traversal.Direction;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;

public interface MapInterface {

	public void activateCell(MapCell cell);
	public void activateCell(Point2D coordinate);
	
	public void fogCells(List<Point2D> targets);
	public void unfogCells();
	
	public MapCell getCell(Point2D coordinate);
	
	public MapCell moveObjectToCell(MapCell target);
	public MapCell moveObjectToCell(Point2D target);
	
	public void move(KeyCode direction);
	
	public void recenter(Point2D center);
}
