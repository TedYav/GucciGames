package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;

/**
 *
 * @author Sally Al
 *
 */
public abstract class DefaultFactory extends AFactory{

	private Properties prop;

	public DefaultFactory() {
		prop = new Properties();
		try {
			prop.load(getStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract InputStream getStream();
	@Override
	protected Constructor makeConstractor(Map<String, ObjParam> mapObjectParams,ObjParamValue objParamValue, Class<?>[] myParameters) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
			Constructor c = Class.forName(prop.getProperty(objParamValue.getName())).getConstructor(myParameters);
			return c;
		}
	}




