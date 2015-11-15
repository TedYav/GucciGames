package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.main;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.sun.javafx.scene.traversal.Direction;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import voogasalad_GucciGames.gameplayer.controller.GameEngineInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapCanvas;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;

public class MainMap extends MapCanvas implements Observer{
	
	public MainMap(GameScene scene, GameEngineInterface game) {
		super(scene, game);
	
	}

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activateCell(MapCell cell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateCell(Point2D coordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapCell getCell(Point2D coordinate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fogCells(List<Point2D> targets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfogCells() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapCell moveObjectToCell(MapCell target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapCell moveObjectToCell(Point2D target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recenter(Point2D center) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
