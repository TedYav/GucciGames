// This entire file is part of my masterpiece.
// Sally Al Khamees

package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ParamObjParser;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameAuthoring.model.factories.ObjectValues;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;


public class ActionFactory extends DefaultFactory {

    private Map<String, RuleParams> myRules = new HashMap<String, RuleParams>();
    private Map<String, ObjParam> myOutcomes = new HashMap<String, ObjParam>();
    private OutcomeFactory outcomeFactory = new OutcomeFactory();
    private RuleFactory ruleFactory = new RuleFactory();
    protected Properties prop;
    public ActionFactory () {
        prop =  addProperties();
        ParamObjParser parser = new ParamObjParser();
        initializeOutcomes(parser);
        initializeRules(parser);

    }

    private void initializeRules (ParamObjParser parser) {
        Set<RuleParams> rules = parser.getRules();
        for (RuleParams param : rules) {
            myRules.put(param.getName(), param);
        }
    }

    private void initializeOutcomes (ParamObjParser parser) {
        Set<ObjParam> outcomes = parser.getOutcomes();
        for (ObjParam param : outcomes) {
            myOutcomes.put(param.getName(), param);
        }
    }

    @Override
    public InputStream getStream () {
        return FactoryPropertyFilePath.PATH_TO_ACTION_PROPERTIES.getValue();
    }

    public MapObjectEvent createAction (Map<String, ActionParam> params, ActionParamsValue value) throws ClassNotFoundException, NoSuchMethodException, SecurityException,InstantiationException, IllegalAccessException, IllegalArgumentException,InvocationTargetException {

        String actionName = prop.getProperty(value.getName());
        MapObjectEvent actionInstance = doReflection(actionName);
        addRules(value, actionInstance);
        addOutcomes(value, actionInstance);
        return actionInstance;

    }

    @SuppressWarnings("unchecked")
    private MapObjectEvent doReflection (String actionName) throws ClassNotFoundException, NoSuchMethodException,InstantiationException, IllegalAccessException,InvocationTargetException {
        Class<MapObjectEvent> action = (Class<MapObjectEvent>) Class.forName(actionName);
        Constructor<MapObjectEvent> actionConstructor = action.getDeclaredConstructor();
        MapObjectEvent actionInstance = actionConstructor.newInstance();
        return actionInstance;
    }

    private void addOutcomes (ActionParamsValue value, MapObjectEvent actionInstance) throws ClassNotFoundException, NoSuchMethodException,InstantiationException, IllegalAccessException, InvocationTargetException {
        for (OutcomeParamValue param : value.getAllOutcomes()) {
            actionInstance.addOutcome((Outcome)outcomeFactory.create(myOutcomes, new ObjectValues(param)));
        }
    }

    private void addRules (ActionParamsValue value,MapObjectEvent actionInstance) throws NoSuchMethodException,ClassNotFoundException,InstantiationException, IllegalAccessException,InvocationTargetException {
        for (String ruleName : value.getAllRules()) {
            actionInstance.addRule(ruleFactory.createRule(ruleName));
        }
    }

}
