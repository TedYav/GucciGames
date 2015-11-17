package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParams.MainGameEngineCommunicationParams;
import voogasalad_GucciGames.gameEngine.gameConditions.GridCoordinateParameters;
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
		return ((MapObjectType) myObjectType).getActionStrings();
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

	public GridCoordinateParameters performAction(String action,
			MainGameEngineCommunicationParams mainGameEngineCommunicationParams) {
		// TODO Auto-generated method stub
		return (GridCoordinateParameters) myObjectType.getAction(action).executeAction(mainGameEngineCommunicationParams, ownerID);
	}

}
