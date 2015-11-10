package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinate;

public abstract class MapObjectType {

	
	private String myName;
	private String myImagePath;
	
	//size
	
	private TargetCoordinate myLocation;
	
	protected Map<String, MapObjectCharacteristic> myCharacteristics; //test
	protected Map<String, MapObjectAction> myAbilities; //test

	

	public MapObjectType(String name, String imagePath){
		myName = name;
		myImagePath = imagePath;
		
		myCharacteristics = new HashMap<String,  MapObjectCharacteristic>();
		myAbilities = new HashMap<String, MapObjectAction>();
		
	}
	
	//public void changeHealth(double healthDiff){
	//	myHealthCharacteristic.changeHealth(healthDiff);
	//}

	public String getMyName() {
		return myName;
	}

	
	public String toString(){
		return myName;
	}
	
	public MapObjectAction getAction(String name){
		return myAbilities.get(name);
	}
	
	
	
	
}
