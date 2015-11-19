package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.CommunicationParams.ActionToGamePlayerParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MainGameEngineCommunicationParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public class MapObject implements PlayerMapObjectInterface{
	private MapObjectType myObjectType;
	private ATargetCoordinate myCoordinate;
	private int myOwnerID;
	private int myLayer;
	//sizes
	private  Map<String, AMapObjectCharacteristic> myCharacteristics; //test
	
	public MapObject(MapObjectType type, ATargetCoordinate coor, int ownerID){
		this.myObjectType = type;
		this.myCoordinate = coor;
		this.myOwnerID=ownerID;
		myLayer=0;
		
		myCharacteristics = new TreeMap<String,  AMapObjectCharacteristic>();

		
	}
	
    public MapObject(MapObjectType type, ATargetCoordinate coor, int ownerID, int layer){
        this(type,coor,ownerID);
        myLayer=layer;
}

	
	
	public AMapObjectCharacteristic getCharacteristic(String name){
		return this.myCharacteristics.get(name);
	}

	public void addCharacteristic(String name, AMapObjectCharacteristic characteristic){
		this.myCharacteristics.put(name, characteristic);
	}

	public boolean hasCharacteristic(String name){
		return this.myCharacteristics.containsKey(name);
	}

	public List<String> getCharacteristicStrings(){
		return new ArrayList<String>(this.myCharacteristics.keySet());
	}
	
	public boolean isTile(){
		return myCharacteristics.containsKey("TileCharacteristic") || this.getObjectType().getName().equals("TileCharacteristic");
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
		return this.hasCharacteristic("unit");
	}

	@Override
	public Map<String, String> getAttributes() {
		//return myObjectType.getCharacteristic();
		
		Map<String, String> myAttrMap = new TreeMap<String, String>();
		for(String s : myCharacteristics.keySet()){
			myAttrMap.put(s, myCharacteristics.get(s).toString());
		}
		
		return myAttrMap;
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
		
		System.out.println(myObjectType);
		System.out.println("action"+myObjectType.getAction(action));
		return (ActionToGamePlayerParameters) myObjectType.getAction(action).executeAction(basicParameters, myOwnerID);

	}
	
	public GridCoordinateParameters performRequest(String action,
			BasicParameters basicParameters) {
		// TODO Auto-generated method stub
		
		return (GridCoordinateParameters) myObjectType.getRequest("WhereTo" + action).executeAction(basicParameters, myOwnerID);

		
	}
	public void setOwnerID(int playerID) {
		myOwnerID = playerID;
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return ((HealthCharacteristic) getCharacteristic("HealthCharacteristic")).getCurrentHealth() < 0;
	}

	@Override
	public List<ATargetCoordinate> getActionTargets(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRemoved() {
		// TODO Auto-generated method stub
		return isDead();
	}



}
