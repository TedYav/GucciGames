package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TreeMap;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.ConditionParams;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;

public class CharacteristicFactory {
	
	private Map<String, AMapObjectCharacteristic> objectCharacteristics;
	private String CHARACTERISTIC_PROPERTIES_PATH = "MapObjectCharacteristics.properties";
//	private ResourceBundle resources = ResourceBundle.getBundle(CHARACTERISTIC_PROPERTIES_PATH);
	private InputStream inputStream;
	
	public CharacteristicFactory(){
		
	}
	
	private void initializeCharacteristics(){		
		inputStream = getClass().getResourceAsStream(CHARACTERISTIC_PROPERTIES_PATH);
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		objectCharacteristics = new TreeMap<>();
		for (Object name: prop.keySet()){
			String key = prop.getProperty((String) name);
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

