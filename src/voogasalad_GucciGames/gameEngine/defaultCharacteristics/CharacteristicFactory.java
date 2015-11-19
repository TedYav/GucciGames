package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
	private static String CHARACTERISTIC_PROPERTIES_PATH = "voogasalad_GucciGames.gameEngine.defaultCharacteristics.MapObjectCharacteristics";
	private ResourceBundle resources = ResourceBundle.getBundle(CHARACTERISTIC_PROPERTIES_PATH);
	private InputStream inputStream;
	
	public CharacteristicFactory(){
		
	}
	
//	public static void main(String[] args){
//		CharacteristicFactory cf = new CharacteristicFactory();
//		CharacteristicParams cp = new CharacteristicParams("AttackCharacteristic", 0, 0, 0, false);
//		cf.initializeCharacteristics(cp);
//	}
	
	public Map<String, AMapObjectCharacteristic> initializeCharacteristics(CharacteristicParams charParams){		
		objectCharacteristics = new TreeMap<>();
		for (String name: resources.keySet()){
			System.out.println(name);
			String path = resources.getString(name);
			Class<AMapObjectCharacteristic> characteristic;
			try {
				characteristic = (Class<AMapObjectCharacteristic>) Class.forName(path);
				charParams.setMyName(path);
				Constructor<AMapObjectCharacteristic> moConstructor = characteristic.getDeclaredConstructor(CharacteristicParams.class);
				objectCharacteristics.put(name, moConstructor.newInstance(charParams));
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(objectCharacteristics);
		return objectCharacteristics;
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

