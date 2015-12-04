package voogasalad_GucciGames.gameplayer.controller;

import java.util.List;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;

import java.util.Observer;

import javafx.scene.image.Image;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneInterface;
import voogasalad_GucciGames.gameplayer.scenes.concrete.MainGameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;


//TODO:
// NEEDS WAY TO GET CURRENT PLAYER

public interface GameControllerInterface{
	
	public void activateCell(MapCell cell);
	public List<MapCell> getActiveCells();
	
	public void setActiveMapObject(PlayerMapObjectInterface mapObj);
	public PlayerMapObjectInterface getActiveMapObject();
	public void addActiveMOObserver(Observer o);
	
	public List<TargetCoordinateSingle> setActionInProgress(String action, PlayerMapObjectInterface unit);
	public String getActionInProgress();
	public void cancelAction();
	
	public MapInterface getMap();
	
	// TODO: remove from interface
	public void setMap(MapInterface map);
	
//	public void setEngine(GameEngineToGamePlayerInterface engine);
	public GameEngineToGamePlayerInterface getEngine();
	public GameInfo getGame();
	
	public GameLoader getLoader();
	
	public List<PlayerMapObjectInterface> getInitialState();
	
	public void endTurn();
	
	public Image requestImage(String imageURI);
	
	public boolean actionInProgress();
	
//	public <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<T> eventHandler);
//	public <T extends Event> void addEventFilter(EventType<T> eventType, EventHandler<T> eventHandler);
//	
	// TODO: refactor out
	void performActionInProgress(Point2D target);
}
