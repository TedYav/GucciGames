package voogasalad_GucciGames.gameAuthoring.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * A util class to load property file
 * Property file must be in resources package, end with .properties
 * 
 * @author Mike Ma (ym67)
 *
 */
public class PropertyLoader {

	private static final String PATH = PropertyLoader.class.getPackage()+"resources/";
	private static final String EXTENSION = ".properties";

	public Properties load(String fileName) throws IOException {
		fileName = PATH + fileName + EXTENSION;
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
		if (input == null) {
			throw new FileNotFoundException("Cannot find " + fileName);
		}
		prop.load(input);
		input.close();
		return prop;
	}
}
