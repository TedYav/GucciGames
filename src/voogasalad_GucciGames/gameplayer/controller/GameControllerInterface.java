package voogasalad_GucciGames.gameplayer.controller;

import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;

//TODO:
// NEEDS WAY TO GET CURRENT PLAYER

public interface GameControllerInterface{

	public void activateCell(MapCell cell);
	public List<MapCell> getActiveCells();
	
	public void setActionInProgress(String action);
	public String getActionInProgress();
	public void cancelAction();
	
	public MapInterface getMap();
	
	// TODO: remove from interface
	public void setMap(MapInterface map);
	
//	public void setEngine(GameEngineToGamePlayerInterface engine);
//	public GameEngineToGamePlayerInterface getEngine();
	
	public Map<String, CellUnit> getInitialState();
	
	public void endTurn();
	
	public Image requestImage(String imageURI);
}
