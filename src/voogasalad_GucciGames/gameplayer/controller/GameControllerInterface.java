package voogasalad_GucciGames.gameplayer.controller;

import java.util.List;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;

import java.util.Observer;
import javafx.scene.image.Image;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes.GameSceneInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes.MainGameScene;


//TODO:
// NEEDS WAY TO GET CURRENT PLAYER

public interface GameControllerInterface{
	
	public void activateCell(MapCell cell);
	public List<MapCell> getActiveCells();
	
	public void setActiveMapObject(PlayerMapObjectInterface mapObj);
	public PlayerMapObjectInterface getActiveMapObject();
	public void addMOObserver(Observer o);
	
	public void setActionInProgress(String action, PlayerMapObjectInterface unit);
	public String getActionInProgress();
	public void cancelAction();
	
	public MapInterface getMap();
	
	// TODO: remove from interface
	public void setMap(MapInterface map);
	
//	public void setEngine(GameEngineToGamePlayerInterface engine);
	public GameEngineToGamePlayerInterface getEngine();
	
	public Map<String, CellUnit> getInitialState();
	
	public void endTurn();
	
	public Image requestImage(String imageURI);
	
	public boolean actionInProgress();
	
	public <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<T> eventHandler);
	public <T extends Event> void addEventFilter(EventType<T> eventType, EventHandler<T> eventHandler);
	
	// TODO: refactor out
	public void setScene(GameSceneInterface scene);
	void performActionInProgress(Point2D target);
}
