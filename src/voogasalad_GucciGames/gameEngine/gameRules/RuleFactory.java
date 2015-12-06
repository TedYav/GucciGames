package voogasalad_GucciGames.gameEngine.gameRules;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;

/**
 *
 * @author Sally Al
 *
 */
public class RuleFactory {
	private static final String PATH_TO_RULE_PROPERTIES = "rulePath.properties";
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

	public BasicParameters createRule(BasicParameters basicParams)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String actionName = "";
		Class<Rules> rule = (Class<Rules>) Class.forName(prop.getProperty(actionName));
		Constructor<Rules> ruleConstructor = rule.getDeclaredConstructor(RuleParams.class, BasicParameters.class);
		Rules ruleInstance = ruleConstructor.newInstance(basicParams);


		// need to add code for groovy
		return basicParams;
	}

}
