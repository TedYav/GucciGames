package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import groovy.ui.SystemOutputInterceptor;
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
	protected Constructor makeConstractor(ObjParamValue objParamValue, Class<?>[] myParameters) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		System.out.println("making condition in default factory");
		System.out.println(objParamValue);
		System.out.println(objParamValue.getName());
		System.out.println(prop);
		System.out.println(prop.getProperty(objParamValue.getName()));
		for(Class<?> c: myParameters){
			System.out.println(c.getName());
		}
		System.out.println(myParameters.length);
		Constructor c = Class.forName(prop.getProperty(objParamValue.getName())).getConstructor(myParameters);
			return c;
		}
	}




