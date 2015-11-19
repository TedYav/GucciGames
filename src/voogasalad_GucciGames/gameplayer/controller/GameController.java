package voogasalad_GucciGames.gameplayer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;

import java.util.Observer;
import javafx.scene.image.Image;
import voogasalad_GucciGames.datastructures.Coordinate;
import voogasalad_GucciGames.datastructures.ImageDatabase;
import voogasalad_GucciGames.gameEngine.CommunicationParams.ActionToGamePlayerParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.controller.dummy.ADummy;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes.GameSceneInterface;

public class GameController implements GameControllerInterface {

	private GameEngineToGamePlayerInterface myEngine;
	private MapInterface myMap;
	private ImageDatabase myImageDatabase;
	private GameSceneInterface myScene;
	private PlayerMapObjectInterface myTargetUnit;
	
	// TODO: factor into component
	private String myActionInProgress;
	private PlayerMapObjectInterface activeMapObject;
	private List<Observer> activeMOObservers;
	private List<TargetCoordinateSingle> possibleMoves;

	public GameController(GameEngineToGamePlayerInterface engine){
		myEngine = engine;
		myImageDatabase = new ImageDatabase();
		myActionInProgress = "";
		activeMOObservers=new ArrayList<Observer>();
	}
	
	@Override
	public void activateCell(MapCell cell) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MapCell> getActiveCells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TargetCoordinateSingle> setActionInProgress(String action, PlayerMapObjectInterface unit) {
		myActionInProgress = action;
		myTargetUnit = unit;
		
		GridCoordinateParameters myParameters= myEngine.getPossibleCoordinates(action, unit);
		
		
		//SORRY FOR THE TIME BEING: THIS WILL BE FIXED IN THE FUTURE
		if(myParameters == null){
			return new ArrayList<TargetCoordinateSingle>();
		}
		else{
			return myEngine.getPossibleCoordinates(action, unit).getListOfCoordinates();
		}
	}

	@Override
	public String getActionInProgress() {
		return myActionInProgress;
	}
	
	@Override
	public void cancelAction() {
		myActionInProgress = "";
		myTargetUnit = null;
	}
	
	@Override
	public void performActionInProgress(Point2D target){
		ActionToGamePlayerParameters params = myEngine.performAction(myActionInProgress, activeMapObject, Coordinate.PointToCoordinate(target));
		cancelAction();
		
		
		//// SORRY FOR THE TIME BEING
	
	        for (TargetCoordinateSingle coord: possibleMoves) {
	            if (target.getX()==coord.getCenterX() && target.getY()==coord.getCenterY()) {
	                 params= myEngine.performAction(myActionInProgress, activeMapObject, Coordinate.PointToCoordinate(target));
	                 cancelAction();
	                 List<PlayerMapObjectInterface> result;
	             	if(params != null){
	            		result = params.getChangedUnits();
	            		}
	            		else{
	            		result = new ArrayList<PlayerMapObjectInterface>();
	            		}
	                 
	                 myMap.update(result);
	                 break;
	            }
	        } 
	}
	
	@Override
	public boolean actionInProgress(){
		return !myActionInProgress.equals("");
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
	public List<PlayerMapObjectInterface> getInitialState() {
		return myEngine.getInitialState();
	}

	@Override
	public void endTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image requestImage(String imageURI) {
		return myImageDatabase.request(imageURI);
	}

//	@Override
//	public <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<T> eventHandler) {
//		myScene.addEventHandler(eventType, eventHandler);
//	}
//
//	@Override
//	public <T extends Event> void addEventFilter(EventType<T> eventType, EventHandler<T> eventHandler) {
//		myScene.addEventFilter(eventType, eventHandler);
//	}

	@Override
	public void setScene(GameSceneInterface scene) {
		myScene = scene;
	}

    @Override
    public void setActiveMapObject (PlayerMapObjectInterface mapObj) {
        activeMapObject=mapObj;
        notifyMOObservers();
    }
    
    public PlayerMapObjectInterface getActiveMapObject() {
        return activeMapObject;
    }

    @Override
    public void addMOObserver (Observer o) {
        activeMOObservers.add(o);
    }
    
    private void notifyMOObservers() {
        for (Observer o: activeMOObservers) {
            o.update(null, activeMapObject);
        }
    }

	@Override
	public GameEngineToGamePlayerInterface getEngine() {
		// TODO Auto-generated method stub
		return myEngine;
	}
}
