package voogasalad_GucciGames.gameplayer.controller;

import java.util.Map;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;

public class GameController implements GameControllerInterface {

	private GameEngineToGamePlayerInterface myEngine;
	private MapInterface myMap;
	
	
	public GameController(GameEngineToGamePlayerInterface engine){
		myEngine = engine;
	}
	
	@Override
	public void activateCell(MapCell cell) {
		// TODO Auto-generated method stub

	}

	@Override
	public MapCell getActiveCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActionInProgress(String action) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getActionInProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public MapInterface getMap() {
		return myMap;
	}

	@Override
	public void setMap(MapInterface map) {
		myMap = map;
	}

	@Override
	public Map<String, CellUnit> getInitialState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endTurn() {
		// TODO Auto-generated method stub

	}

}
