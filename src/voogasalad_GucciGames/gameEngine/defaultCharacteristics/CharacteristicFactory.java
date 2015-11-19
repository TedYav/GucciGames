package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionParams;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class CharacteristicFactory {
	
	private Map<String, AMapObjectCharacteristic> objectCharacteristics;
	private String CHARACTERISTIC_PROPERTIES_PATH = "MapObjectCharacteristics.properties";
	private ResourceBundle resources = ResourceBundle.getBundle(CHARACTERISTIC_PROPERTIES_PATH);
	
	public CharacteristicFactory(){
		initializeCharacteristics();
	}
	
	private void initializeCharacteristics(){		
		objectCharacteristics = new TreeMap<>();
		for (String name: resources.keySet()){
			String key = resources.getString(name);
			Class<AMapObjectCharacteristic> characteristic;
			try {
				characteristic = (Class<AMapObjectCharacteristic>) Class.forName(key);
				objectCharacteristics.put(key, characteristic.newInstance());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	private Map<String,AMapObjectCharacteristic> addCharacteristic(CharacteristicParams characterParams){
//		if (resources.containsKey(characterParams.getName().toString())){
//			Class<AMapObjectCharacteristic> characteristic = (Class<AMapOBjectCharacteristic>) Class.forName(prop.getProperty(condParams.getName()));
//
//			Constructor<Conditions> condConstructor = condition.getDeclaredConstructor(ConditionParams.class,
//					BasicParameters.class);
//
//			Conditions conditionInstance = condConstructor.newInstance(condParams, params);
//		}
//		
//		return getCharacteristics();
//	}
	
	public Map<String, AMapObjectCharacteristic> getCharacteristics(){
		System.out.println(objectCharacteristics);
		return objectCharacteristics;
	}
	
}

