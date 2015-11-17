package voogasalad_GucciGames.gameEngine.gameRules;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;

/**
 *
 * @author Sally Al
 *
 */
public class RuleFactory {
	private static final String PATH_TO_RULE_PROPERTIES = "voogasalad_GucciGames.resources.gameRules.rulePath";
	private ResourceBundle ruleBundle;
	private CommunicationParams myCommunicationParams;
	private ActionToRuleMap myActionToRuleMap;

	public RuleFactory(CommunicationParams communicationParams, ActionToRuleMap actionToRuleMap) {
		ruleBundle = ResourceBundle.getBundle(PATH_TO_RULE_PROPERTIES);
		myActionToRuleMap=actionToRuleMap;
	}

	public ActionToRuleMap createRule(String actionName) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Object> ruleArgs = new ArrayList<Object>();//instead of passing argument map from gae, get a property file of rule: variable, value pairs//talk to gae first
		if(ruleBundle.containsKey(actionName)){
			//while loop to populate  the map
			Class<Rules> condition = (Class<Rules>) Class.forName(ruleBundle.getString(actionName));
			Constructor<Rules> condConstructor = condition.getDeclaredConstructor(myCommunicationParams.getClass(), List.class);
			Rules ruleInstance = condConstructor.newInstance(myCommunicationParams, ruleArgs);
			myActionToRuleMap.put(actionName, ruleInstance);

		}
		//need to add code for groovy
		return myActionToRuleMap;
	}

}
