package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

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
public class RuleFactory extends DefaultFactory{
	private static final String PATH_TO_RULE_PROPERTIES = "rulesPath.properties";

	private Properties prop;

	public RuleFactory() {
		prop = addProperties();
	}

	public Rules createRule(String ruleName) throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String name = prop.getProperty(ruleName);
		Class<Rules> rule = (Class<Rules>) Class.forName(name);
		Constructor<Rules> ruleConstructor = rule.getDeclaredConstructor();
		Rules ruleInstance = ruleConstructor.newInstance();
		return ruleInstance;
	}


    @Override
    protected InputStream getStream () {
        return getClass().getResourceAsStream(PATH_TO_RULE_PROPERTIES);
    }

}
