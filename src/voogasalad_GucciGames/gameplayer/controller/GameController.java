package voogasalad_GucciGames.gameplayer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import javafx.scene.image.Image;
import voogasalad_GucciGames.gameplayer.datastructures.ImageDatabase;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.PlayerMapObjectInterface;

public class GameController implements GameControllerInterface {

	private GameEngineToGamePlayerInterface myEngine;
	private MapInterface myMap;
	private ImageDatabase myImageDatabase;
	private PlayerMapObjectInterface activeMapObject;
	private List<Observer> activeMOObservers;
	
	public GameController(GameEngineToGamePlayerInterface engine){
		myEngine = engine;
		myImageDatabase = new ImageDatabase();
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

	@Override
	public Image requestImage(String imageURI) {
		return myImageDatabase.request(imageURI);
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
}
