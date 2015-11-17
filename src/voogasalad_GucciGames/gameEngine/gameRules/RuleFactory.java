package voogasalad_GucciGames.gameEngine.gameRules;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;

/**
 *
 * @author Sally Al
 *
 */
public class RuleFactory {
	private static final String PATH_TO_RULE_PROPERTIES = "voogasalad_GucciGames.resources.gameRules.rulePath";
	private ResourceBundle ruleBundle;
	public RuleFactory() {
		ruleBundle = ResourceBundle.getBundle(PATH_TO_RULE_PROPERTIES);

	}

	public BasicParameters createRule(String actionName, BasicParameters communicationParams) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(ruleBundle.containsKey(actionName)){
			Class<Rules> condition = (Class<Rules>) Class.forName(ruleBundle.getString(actionName));
			Constructor<Rules> condConstructor = condition.getDeclaredConstructor();
			Rules ruleInstance = condConstructor.newInstance();
			communicationParams.getActionToRuleMap().put(actionName, ruleInstance);

		}
		//need to add code for groovy
		return communicationParams;
	}

}
