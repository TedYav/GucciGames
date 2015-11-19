package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.CommunicationParams.ActionToGamePlayerParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MainGameEngineCommunicationParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public class MapObject implements PlayerMapObjectInterface{
	private MapObjectType myObjectType;
	private ATargetCoordinate myCoordinate;
	private int myOwnerID;
	private int myLayer;

	public MapObject(MapObjectType type, ATargetCoordinate coor, int ownerID){
		this.myObjectType = type;
		this.myCoordinate = coor;
		this.myOwnerID=ownerID;
		myLayer=0;
	}
	       public MapObject(MapObjectType type, ATargetCoordinate coor, int ownerID, int layer){
	                this(type,coor,ownerID);
	                myLayer=layer;
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
		return myOwnerID;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return myLayer;
	}



	public ActionToGamePlayerParameters performAction(String action,
			BasicParameters basicParameters) {
		// TODO Auto-generated method stub
	
		return (ActionToGamePlayerParameters) myObjectType.getAction(action).executeAction(basicParameters, myOwnerID);

	}
	@Override
	public List<ATargetCoordinate> getActionTargets(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public GridCoordinateParameters performRequest(String action,
			BasicParameters basicParameters) {
		// TODO Auto-generated method stub
		
		return (GridCoordinateParameters) myObjectType.getRequest("WhereTo" + action).executeAction(basicParameters, myOwnerID);

		
	}
	public void setOwnerID(int playerID) {
		myOwnerID = playerID;
	}

}
