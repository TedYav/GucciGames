// This entire file is part of my masterpiece.
// Daniel McKee

package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;

/**
 *
 * @author Sally Al and Daniel McKee
 *
 */
public abstract class AFactory<T> {
	private Properties prop;
	private StringTypeParser myParser;

	public AFactory() {
		myParser = new StringTypeParser();
		prop = new Properties();
		try {
			prop.load(getStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract InputStream getStream();

	@SuppressWarnings("unchecked")
	public T create(Map<String, ObjectParam> mapObjectParams, ObjParamValue objParamValue) {
		;
		try {
			return (T) makeObject(mapObjectParams, objParamValue);
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException | TypeParseExeption e) {
			throw new CreatePropertyException(e.getMessage());
		}
	}

	private Object makeObject(Map<String, ObjectParam> mapObjectParams, ObjParamValue objParamValue)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, TypeParseExeption {

		Map<String, String> nameToTypeMap = mapObjectParams.get(objParamValue.getName()).getParamTypeMap();
		Map<Integer, String> orderToNameMap = mapObjectParams.get(objParamValue.getName()).getParamOrderMap();
		Map<String, String> nameToValueMap = objParamValue.getMap();

		Class<?>[] myParameters = new Class<?>[objParamValue.numParams()];
		for (int i = 0; i < myParameters.length; i++) {
			myParameters[i] = TypeMap.getType(nameToTypeMap.get(orderToNameMap.get(i)));
		}

		Object[] initargs = new Object[myParameters.length];

		for (int i = 0; i < initargs.length; i++) {
			Object parsedValue = myParser.getTranslatedValue(nameToTypeMap.get(orderToNameMap.get(i)),
					nameToValueMap.get(orderToNameMap.get(i)));
			initargs[i] = parsedValue;
		}

		Constructor<?> c = makeConstractor(objParamValue, myParameters);
		Object myObject = c.newInstance(initargs);

		return myObject;

	}

	protected Constructor<?> makeConstractor(ObjParamValue objParamValue, Class<?>[] myParameters)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		Constructor<?> c = Class.forName(prop.getProperty(objParamValue.getName())).getConstructor(myParameters);
		return c;
	}

}
