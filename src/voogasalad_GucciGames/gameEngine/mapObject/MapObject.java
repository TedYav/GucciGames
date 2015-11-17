package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.dummy.MapObjectBasicType;

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
		return myObjectType.getAttributes();
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
		return myObjectType.getActionTypes();
	}

	@Override
	public List<ATargetCoordinate> getActionTargets(String action) {
		// TODO Auto-generated method stub
		return myObjectType.getActionTarget(action);
	}

	@Override
	public void performAction(String action, ATargetCoordinate coordinate) {

		myObjectType.getAction(action).action(communication, targetCoords)
	}

	@Override
	public int getPlayerID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
