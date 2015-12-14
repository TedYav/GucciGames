
package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser.TypeMap;
import voogasalad_GucciGames.gameEngine.groovyEngine.AGroovyCustomObject;
import voogasalad_GucciGames.gameEngine.groovyEngine.GameGroovyEngine;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyCustomCharacteristic;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyLoader;

/**
 *
 * @author Sally Al
 *
 */
public class GroovyMapChars extends GroovyFactory {
	// we own this map GobjParam
	// GCharParamValue from ying to create an instance
	// this factory is created after two processes 1. creating the outline of a
	// char 2. create an instance of the char

	public Object create(Map<String, GCharParam> GobjParam, ObjParamValue GCharParamValue)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// param name to type
		Map<String, String> typeMap = new TreeMap<String, String>(
				GobjParam.get(GCharParamValue.getName()).getParamOrderMap());
		// param name to value
		Map<String, String> valueMap = GCharParamValue.getMap();

		Class<?>[] myParameters = new Class<?>[GCharParamValue.numParams()];

		Object[] initargs = new Object[myParameters.length];

		ArrayList<String> myTypes = new ArrayList<String>();
		myTypes.addAll(typeMap.keySet());
		Collections.sort(myTypes);

		List<String> paramKeys = new ArrayList<>(typeMap.keySet());
		for (int i = 0; i < paramKeys.size(); i++) {
			myParameters[i] = TypeMap.getType(typeMap.get(paramKeys.get(i)));
		}

		for (int i = 0; i < initargs.length; i++) {
			Class<?> constr = myParameters[i];

			initargs[i] = valueMap.get(myTypes.get(i));
			initargs[i] = getTranslatedValue(typeMap.get(myTypes.get(i)), valueMap.get(myTypes.get(i)));

		}

		// create constructor;
		// this creates the loader every time and adds the specific char ying
		// send to us with the value thing and send it back
		// save it in a list
		GroovyCustomCharacteristic g = new GroovyCustomCharacteristic(GCharParamValue.getName(),
				(GobjParam.get(GCharParamValue.getName()).getAllParams()));
		GameGroovyEngine engine = new GameGroovyEngine();
		List<AGroovyCustomObject> groovyCustom = new ArrayList<>();
		groovyCustom.add(g);
		GroovyLoader loader = engine.createLoader(groovyCustom);
		Constructor<?> c = loader.load(GCharParamValue.getName()).getDeclaredConstructor(myParameters);
		Object myObject = c.newInstance(initargs);
		System.out.println(myObject.getClass().toString());

		return myObject;

	}

}
