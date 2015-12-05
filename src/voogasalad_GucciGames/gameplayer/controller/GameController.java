package voogasalad_GucciGames.gameplayer.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

import java.util.Observer;

import javafx.scene.image.Image;
import voogasalad_GucciGames.datastructures.Coordinate;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GameInfoToGamePlayer;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.gameloader.GameControllerLoader;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameWindowManager;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.helpers.ResourceManager;

public class GameController implements GameControllerInterface, GameControllerAdvancedInterface, GameControllerLoader {

	private GameWindowManager myManager;
	
	private GameEngineToGamePlayerInterface myCurrentEngine;
	private MapInterface myMap;
	private ResourceManager myResourceManager;
	private PlayerMapObjectInterface myTargetUnit;
	private GameInfoToGamePlayer myGame;
	
	// TODO: factor into component
	private String myActionInProgress;
	private PlayerMapObjectInterface activeMapObject;
	private List<Observer> activeMOObservers;
	private List<TargetCoordinateSingle> possibleMoves;
	
	private GameLoader myLoader;

	public GameController(GameWindowManager manager){
		myManager = manager;
		myResourceManager = new ResourceManager();
		myActionInProgress = "";
		activeMOObservers=new ArrayList<Observer>();
		possibleMoves = new ArrayList<TargetCoordinateSingle>();
		myLoader = new GameLoader(this);
	}
	
	public void loadGame(GameInfo game){
		myGame=game;
		loadLevel(1);
	}
	
	@Override
	public void loadLevel(int levelID){
		if(myGame.getLevels().containsKey(levelID)){
			myCurrentEngine = myGame.getLevels().get(levelID).getGameEngine();
		}
	}
	
	@Override
	public List<TargetCoordinateSingle> setActionInProgress(String action, PlayerMapObjectInterface unit) {
		myActionInProgress = action;
		myTargetUnit = unit;
//	
		possibleMoves = myTargetUnit.performRequest(action).getListOfCoordinates();
		
		return possibleMoves;
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
	                 myManager.refresh();
	                 break;
	            }
	        } 
	        //workaround for canceling action by clicking outside of action range (increments action i think?)
	        cancelAction();
	        myMap.update(new ArrayList<PlayerMapObjectInterface>());
	        myManager.refresh();
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
		return myCurrentEngine.getInitialState();
	}

	@Override
	public void endTurn() {
		// TODO Auto-generated method stub
	        myCurrentEngine.endTurn();
	        myManager.refresh();
	}

	@Override
	public ResourceManager getResource(){
		return myResourceManager;
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
		return myCurrentEngine;
	}
	@Override
	public GameInfoToGamePlayer getGame() {
	    return myGame;
	}

	public GameLoader getLoader() {
		return myLoader;
	}
}
