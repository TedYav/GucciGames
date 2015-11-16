package voogasalad_GucciGames.gameEngine.mapObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapObjectType{

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private String myName;
	private String myImagePath;
	
	//sizes
	
	protected Map<String, AMapObjectCharacteristic> myCharacteristics; //test
	protected Map<String, IMapObjectAction> myAbilities; //test

	

	public MapObjectType(String name, String imagePath){
		myName = name;
		myImagePath = imagePath;
		
		// TreeMap so alphabetized when giving to front end
		myCharacteristics = new TreeMap<String,  AMapObjectCharacteristic>();
		myAbilities = new TreeMap<String, IMapObjectAction>();
		
	}
	
	//public void changeHealth(double healthDiff){
	//	myHealthCharacteristic.changeHealth(healthDiff);
	//}

	public String getMyName() {
		return myName;
	}

	public String getMyImagePath() {
		return myImagePath;
	}
	
	public String toString(){
		return myName;
	}
	
	public IMapObjectAction getAction(String name){
		return myAbilities.get(name);
	}
	
	public AMapObjectCharacteristic getCharacteristic(String name){
		return this.myCharacteristics.get(name);
	}
	
	public List<String> getActionStrings(){
		return new ArrayList<String>(this.myAbilities.keySet());
	}
	
	public List<String> getCharacteristicStrings(){
		return new ArrayList<String>(this.myCharacteristics.keySet());
	}
	
	public void addAction(String name, IMapObjectAction action){
		this.myAbilities.put(name, action);
	}
	
	public void addCharacteristic(String name, AMapObjectCharacteristic characteristic){
		this.myCharacteristics.put(name, characteristic);
	}
	
	public boolean hasCharacteristic(String name){
		return this.myCharacteristics.containsKey(name);
	}
	
	public boolean hasAction(String name){
		return this.myAbilities.containsKey(name);
	}
	
	
}
