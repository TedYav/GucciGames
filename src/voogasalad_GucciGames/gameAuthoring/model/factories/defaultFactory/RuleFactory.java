// This entire file is part of my masterpiece.
// Sally Al Khamees
package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;


/**
 *
 * @author Sally Al and Daniel McKee
 *
 */
public class RuleFactory extends DefaultFactory {

    private Properties prop;

    public RuleFactory () {
        prop = addProperties();
    }

    public Rules createRule (String ruleName) throws NoSuchMethodException, SecurityException,
                                              ClassNotFoundException,
                                              InstantiationException, IllegalAccessException,
                                              IllegalArgumentException, InvocationTargetException {

        String name = prop.getProperty(ruleName);
        Class<Rules> rule = (Class<Rules>) Class.forName(name);
        Constructor<Rules> ruleConstructor = rule.getDeclaredConstructor();
        Rules ruleInstance = ruleConstructor.newInstance();
        return ruleInstance;
    }

    @Override
    public InputStream getStream () {
        return FactoryPropertyFilePath.PATH_TO_RULE_PROPERTIES.getValue();
    }

}
