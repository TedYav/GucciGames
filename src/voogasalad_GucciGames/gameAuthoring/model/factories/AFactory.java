package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;

/**
 *
 * @author Sally Al
 *
 */
public abstract class AFactory {
	private Properties prop;

	public AFactory() {
		prop = new Properties();
		try {
			prop.load(getStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected abstract InputStream getStream();


	public Object create(Map<String, ObjParam> mapObjectCharParams, ObjParamValue objParamValue)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// param name to type
		Map<String, String> typeMap = mapObjectCharParams.get(objParamValue.getName()).getParamTypeMap();
		// order to param name
		Map<Integer, String> orderMap = mapObjectCharParams.get(objParamValue.getName()).getParamOrderMap();
		// param name to value
		Map<String, String> valueMap = objParamValue.getMap();

		Class<?>[] myParameters = new Class<?>[objParamValue.numParams()];
		for (int i = 0; i < myParameters.length; i++) {
			myParameters[i] = TypeMap.getType(typeMap.get(orderMap.get(i)));

		}

		Object[] initargs = new Object[myParameters.length];

		for (int i = 0; i < initargs.length; i++) {
			Class<?> constr = myParameters[i];
			initargs[i] = (valueMap.get(orderMap.get(i)));
			initargs[i] = getTranslatedValue(typeMap.get(orderMap.get(i)), valueMap.get(orderMap.get(i)));

		}

		Constructor c = Class.forName(prop.getProperty(objParamValue.getName())).getConstructor(myParameters);

		Object myObject = c.newInstance(initargs);
		return myObject;

	}

	private Object getTranslatedValue(String type, String value) {
		if (type.equals("int"))
			return Integer.parseInt(value);
		if (type.equals("double"))
			return Double.parseDouble(value);
		if (type.equals("String"))
			return value;
		if (type.equals("boolean"))
			return Boolean.parseBoolean(value);
		return null;

	}

}
