package voogasalad_GucciGames;

import java.util.List;
import java.util.Map;

public abstract class MapObjectType {

	
	private String myName;
	private String myImagePath;
	
	protected TargetCoordinate myLocation;
	
	protected Map<String, MapObjectCharacteristic> myCharacteristics;
	protected Map<String, MapObjectAction> myAbilities;

	protected HealthCharacteristic myHealthCharacteristic;
	

	public void changeHealth(double healthDiff){
		myHealthCharacteristic.changeHealth(healthDiff);
	}
	
	
	
}
