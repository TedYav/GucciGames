package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class CharacteristicHandler {
	private CharacteristicFactory myCharacteristicFactory;
	private Map<String,?> characteristicMap;
	
	public CharacteristicHandler(){
		myCharacteristicFactory = new CharacteristicFactory();
		characteristicMap = myCharacteristicFactory.getCharacteristics();
	}
	
	public Map<String,?> getCharacteristics(Object[] objects){
		Map<String,AMapObjectCharacteristic> temp = new TreeMap<>();
		for (Object key: objects){
			temp.put((String) key, (AMapObjectCharacteristic) characteristicMap.get(key));
		}
		return temp;
	}
	
	public AMapObjectCharacteristic getCharacteristic(String name) {
		//needs throws exception if name not found
		return (AMapObjectCharacteristic) characteristicMap.get(name);
	}
	
}
