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
	
	public Map<String,?> getCharacteristics(List<String> characteristics){
		Map<String,AMapObjectCharacteristic> temp = new TreeMap<>();
		for (String key: characteristics){
			temp.put(key, (AMapObjectCharacteristic) characteristicMap.get(key));
		}
		return temp;
	}
	
}
