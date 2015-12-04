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
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.controller.dummy.ADummy;
import voogasalad_GucciGames.gameplayer.gameloader.GameControllerLoader;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindowManager;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;

public class GameController implements GameControllerInterface, GameControllerLoader {

	private GameWindowManager myManager;
	
	private GameEngineToGamePlayerInterface myEngine;
	private MapInterface myMap;
	private ImageDatabase myImageDatabase;
	private PlayerMapObjectInterface myTargetUnit;
	private GameInfo myGame;
	
	// TODO: factor into component
	private String myActionInProgress;
	private PlayerMapObjectInterface activeMapObject;
	private List<Observer> activeMOObservers;
	private List<TargetCoordinateSingle> possibleMoves;

	private GameLoader myLoader;

	public GameController(GameWindowManager manager){
		myManager = manager;
		myImageDatabase = new ImageDatabase();
		myActionInProgress = "";
		activeMOObservers=new ArrayList<Observer>();
		possibleMoves = new ArrayList<TargetCoordinateSingle>();
		myLoader = new GameLoader(this);
	}
	
	public void loadGame(GameInfo game){
		myGame=game;
		myEngine = game.getEngine();
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
//	
		possibleMoves = myTargetUnit.performRequest(action).getListOfCoordinates();
		
		return possibleMoves;
		//return unit.getAction(action)(action);
		
//		GridCoordinateParameters myParameters = myEngine.getPossibleCoordinates(action, unit);
//		
//
//		//SORRY FOR THE TIME BEING: THIS WILL BE FIXED IN THE FUTURE
//		if(myParameters == null){
//			return new ArrayList<TargetCoordinateSingle>();
//		}
//		else{
//			//possibleMoves = myEngine.getPossibleCoordinates(action, unit).getListOfCoordinates();
//			//			return possibleMoves;
//		}
		
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
		ChangedParameters params;// = myEngine.performAction(myActionInProgress, activeMapObject, Coordinate.PointToCoordinate(target));
		//cancelAction();
		
		System.out.println("PERFORMING ACTION");
		//// SORRY FOR THE TIME BEING
	
	        for (TargetCoordinateSingle coord: possibleMoves) {
	        	System.out.println("CHECKING COORDINATE: " + coord);
	            if (target.getX()==coord.getCenterX() && target.getY()==coord.getCenterY()) {
	                 params = activeMapObject.performAction(myActionInProgress, Coordinate.PointToCoordinate(target));
	                 cancelAction();
	                 List<PlayerMapObjectInterface> result;
	             	
	            		result = params.getChangedUnits();
	            		System.out.println(result);
	                 
	                 myMap.update(result);
	                 break;
	            }
	        } 
	        //workaround for canceling action by clicking outside of action range (increments action i think?)
	        cancelAction();
	        myMap.update(new ArrayList<PlayerMapObjectInterface>());
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
	    myEngine.endTurn();
	           myManager.refresh();
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
    public void setActiveMapObject (PlayerMapObjectInterface mapObj) {
        activeMapObject=mapObj;
        notifyMOObservers();
    }
    
    public PlayerMapObjectInterface getActiveMapObject() {
        return activeMapObject;
    }

    @Override
    public void addActiveMOObserver (Observer o) {
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
	@Override
	public GameInfo getGame() {
	    return myGame;
	}

	public GameLoader getLoader() {
		return myLoader;
	}
}
