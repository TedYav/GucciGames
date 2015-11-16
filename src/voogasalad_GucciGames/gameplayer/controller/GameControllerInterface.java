package voogasalad_GucciGames.gameplayer.controller;

import java.util.Map;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;

//TODO:
// NEEDS WAY TO GET CURRENT PLAYER

public interface GameControllerInterface{

	public void activateCell(MapCell cell);
	public MapCell getActiveCell();
	
	public void setActionInProgress(String action);
	public String getActionInProgress();
	public void cancelAction();
	
	public MapInterface getMap();
	public void setMap(MapInterface map);
	
//	public void setEngine(GameEngineToGamePlayerInterface engine);
//	public GameEngineToGamePlayerInterface getEngine();
	
	public Map<String, CellUnit> getInitialState();
	
	public void endTurn();
}
