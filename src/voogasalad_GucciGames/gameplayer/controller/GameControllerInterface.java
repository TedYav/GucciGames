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
import voogasalad_GucciGames.helpers.ResourceManager;


//TODO:
// NEEDS WAY TO GET CURRENT PLAYER

public interface GameControllerInterface{
	
	public void setActiveMapObject(PlayerMapObjectInterface mapObj);
	public PlayerMapObjectInterface getActiveMapObject();
	public void addActiveMOObserver(Observer o);
	
	public List<TargetCoordinateSingle> setActionInProgress(String action, PlayerMapObjectInterface unit);
	public String getActionInProgress();
	public void cancelAction();
	
	public MapInterface getMap();
	
	public GameEngineToGamePlayerInterface getEngine();
	public GameInfo getGame();
	
	public GameLoader getLoader();
	
	public List<PlayerMapObjectInterface> getInitialState();
	
	public void endTurn();
		
	public boolean actionInProgress();
	
	public void performActionInProgress(Point2D target);
	public ResourceManager getResource();
}
