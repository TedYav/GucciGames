package voogasalad_GucciGames.gameEngine.mapObject;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinate;

public abstract class MapObjectType {

	
	private String myName;
	private String myImagePath;
	
	protected TargetCoordinate myLocation;
	
	protected Map<String, MapObjectCharacteristic> myCharacteristics;
	protected Map<String, MapObjectAction> myAbilities;

	protected HealthCharacteristic myHealthCharacteristic;
	

	public MapObjectType(String name, String imagePath){
		myName = name;
		myImagePath = imagePath;
	}
	
	public void changeHealth(double healthDiff){
		myHealthCharacteristic.changeHealth(healthDiff);
	}
	
	
	
}
