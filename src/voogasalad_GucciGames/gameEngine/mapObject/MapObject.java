package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public class MapObject implements PlayerMapObjectInterface{
    private MapObjectType myObjectType;
    private ATargetCoordinate myCoordinate;
    private int ownerID;

    public MapObject(MapObjectType type, ATargetCoordinate coor, int ownerID){
    	this.myObjectType = type;
    	this.myCoordinate = coor;
    }

    public MapObjectType getObjectType(){
    	return myObjectType;
    }

    @Override
	public ATargetCoordinate getCoordinate(){
    	return myCoordinate;
    }

    public void setCoordinate(ATargetCoordinate coordinate){
    	this.myCoordinate = coordinate;
    }

	public boolean isUnit() {
		return myObjectType.hasCharacteristic("unit");
	}

	@Override
	public Map<String, String> getAttributes() {
		//return myObjectType.getCharacteristic();
		return new TreeMap<>();
	}

	@Override
	public String getName() {
		return myObjectType.getName();
	}

	@Override
	public String getImageURI() {
		return myObjectType.getImagePath();
	}

	@Override
	public List<String> getActionNames() {
		// TODO Auto-generated method stub
		return myObjectType.getActionStrings();
	}

	@Override
	public int getPlayerID() {
		// TODO Auto-generated method stub
		return ownerID;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	//FIX THIS!!! ERROR WAS CAUSED FROM ActionDisplay.java line 52 
	// myController.getMap().highlightCells(activeMapObject.getActionTargets(name));
	@Override
	public List<ATargetCoordinate> getActionTargets(String name) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}
