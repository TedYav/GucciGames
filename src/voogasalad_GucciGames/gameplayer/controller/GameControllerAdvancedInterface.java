package voogasalad_GucciGames.gameplayer.controller;

import java.util.List;
import java.util.Observer;

import javafx.geometry.Point2D;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GameInfoToGamePlayer;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.helpers.ResourceManager;

public interface GameControllerAdvancedInterface {

	public void setMap(MapInterface map);

	public void setActiveMapObject(PlayerMapObjectInterface mapObj);
	public PlayerMapObjectInterface getActiveMapObject();
	public void addActiveMOObserver(Observer o);
	
	public List<TargetCoordinateSingle> setActionInProgress(String action, PlayerMapObjectInterface unit);
	public String getActionInProgress();
	public void cancelAction();
	
	public MapInterface getMap();
	
	public GameEngineToGamePlayerInterface getEngine();
	public GameInfoToGamePlayer getGame();
	
	public GameLoader getLoader();
	
	public List<PlayerMapObjectInterface> getInitialState();
	
	public void endTurn();
		
	public boolean actionInProgress();
	
	public void performActionInProgress(Point2D target);
	public ResourceManager getResource();
	
}
