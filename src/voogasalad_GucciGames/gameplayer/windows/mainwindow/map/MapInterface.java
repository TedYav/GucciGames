package voogasalad_GucciGames.gameplayer.windows.mainwindow.map;

import java.util.List;

import com.sun.javafx.scene.traversal.Direction;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public interface MapInterface {

	public void activateCell(MapCellInterface cell);
	public void activateCell(Point2D coordinate);
	
	public void fogCells(List<Point2D> targets);
	public void unfogCells();
	
	public MapCellInterface getCell(Point2D coordinate);
	
	public MapCellInterface moveObjectToCell(MapCellInterface target);
	public MapCellInterface moveObjectToCell(Point2D target);
	
	public void move(KeyCode direction);
	
	public void recenter(Point2D center);
}
