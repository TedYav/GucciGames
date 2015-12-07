
package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
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
	@Override
	protected  Constructor<?> makeConstractor(Map<String, ObjParam> mapObjectParams,ObjParamValue objParamValue, Class<?>[] myParameters) throws NoSuchMethodException, SecurityException, ClassNotFoundException{
		GroovyCustomCharacteristic g = new GroovyCustomCharacteristic(objParamValue.getName(),(mapObjectParams.get(objParamValue.getName())).getParamTypeMap());
		GameGroovyEngine engine = new GameGroovyEngine();
		List<AGroovyCustomObject> groovyCustom = new ArrayList<>();
		groovyCustom.add(g);
		GroovyLoader loader = engine.createLoader(groovyCustom);
		Constructor<?> ctor = loader.load(objParamValue.getName()).getDeclaredConstructor(myParameters);
		return ctor;
	}

}
