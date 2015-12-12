
package voogasalad_GucciGames.gameAuthoring.model.factories.groovyFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.factories.TypeMap;
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

	public Object create(Map<String, GCharParam> GobjParam, ObjParamValue GCharParamValue)throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,IllegalAccessException, IllegalArgumentException, InvocationTargetException {

	String name = GCharParamValue.getName();
        Map<String, String> typeMap = new TreeMap<String, String>(GobjParam.get(name).getParamOrderMap());

        Class<?>[] myParameters = initializeParams(GCharParamValue, typeMap);
        Object[] initargs = createArguments(typeMap, GCharParamValue.getMap(), new ArrayList<String>(), myParameters.length);

        List<AGroovyCustomObject> groovyCustom = new ArrayList<AGroovyCustomObject>();
        groovyCustom.add(new GroovyCustomCharacteristic(name, (GobjParam.get(name).getAllParams())));
        GroovyLoader loader = new GameGroovyEngine().createLoader(groovyCustom);
        Constructor<?> c = loader.load(name).getDeclaredConstructor(myParameters);
        Object myObject = c.newInstance(initargs);
        return myObject;

    }

    private Object[] createArguments (Map<String, String> typeMap, Map<String, String> valueMap,ArrayList<String> myTypes, int length) {
        Object[] initargs = new Object[length];
        myTypes.addAll(typeMap.keySet());
        Collections.sort(myTypes);
        for (int i = 0; i < initargs.length; i++) {
            initargs[i] = valueMap.get(myTypes.get(i));
            initargs[i] = getTranslatedValue(typeMap.get(myTypes.get(i)), valueMap.get(myTypes.get(i)));
        }
        return initargs;
    }

    private Class<?>[] initializeParams (ObjParamValue GCharParamValue, Map<String, String> typeMap) {
        Class<?>[] myParameters = new Class<?>[GCharParamValue.numParams()];
        List<String> paramKeys = new ArrayList<>(typeMap.keySet());
        for (int i = 0; i < paramKeys.size(); i++) {
            myParameters[i] = TypeMap.getType(typeMap.get(paramKeys.get(i)));
        }
        return myParameters;
    }


}
