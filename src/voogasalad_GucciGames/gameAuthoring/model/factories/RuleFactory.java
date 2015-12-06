package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al
 *
 */
public class RuleFactory {
	private static final String PATH_TO_RULE_PROPERTIES = "rulesPath.properties";
	InputStream inputStream;
	private Properties prop;

	public RuleFactory() {
		inputStream = getClass().getResourceAsStream(PATH_TO_RULE_PROPERTIES);
		prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Rules createRule(String ruleName) throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<Rules> rule = (Class<Rules>) Class.forName(prop.getProperty(ruleName));
		Constructor<Rules> ruleConstructor = rule.getDeclaredConstructor();
		Rules ruleInstance = ruleConstructor.newInstance();
		return ruleInstance;
	}

}
