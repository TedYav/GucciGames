package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.IOException;
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
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

public class ActionFactory {
	private Map<String, RuleParams> myRules = new HashMap<String, RuleParams>();
	private Map<String, ObjParam> myConditions = new HashMap<String, ObjParam>();
	private Map<String, ObjParam> myOutcomes = new HashMap<String, ObjParam>();

	InputStream inputStream;
	private Properties prop;

	private TypeMap typeMap = new TypeMap();
	private static final String PATH_TO_RULE_PROPERTIES = "actionsPath.properties";

	private OutcomeFactory outcomeFactory = new OutcomeFactory();
	private RuleFactory ruleFactory = new RuleFactory();

	public ActionFactory() {
		inputStream = getClass().getResourceAsStream(PATH_TO_RULE_PROPERTIES);
		prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ParamObjParser parser = new ParamObjParser();
		Set<ObjParam> conditions = parser.getConditions();
		for (ObjParam param : conditions) {
			myConditions.put(param.getName(), param);
		}
		Set<ObjParam> outcomes = parser.getOutcomes();
		for (ObjParam param : outcomes) {
			myOutcomes.put(param.getName(), param);
		}
		Set<RuleParams> rules = parser.getRules();
		for (RuleParams param : rules) {
			myRules.put(param.getName(), param);
		}

	}

	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_RULE_PROPERTIES);
	}

	public MapObjectEvent createAction(Map<String, ActionParam> params, ActionParamsValue value)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// constructs empty Action object
		System.out.println(value.getName() + " at " + prop.getProperty(value.getName()));
		Class<MapObjectEvent> action = (Class<MapObjectEvent>) Class.forName(prop.getProperty(value.getName()));
		Constructor<MapObjectEvent> actionConstructor = action.getDeclaredConstructor();
		MapObjectEvent actionInstance = actionConstructor.newInstance();

		// NOT USING BECAUSE NOT ENFORCING CREATION OF CERTAIN CHARS
		// ActionParam actionParam = params.get(value.getName());

		// construct and add rules
		for (String ruleName : value.getAllRules()) {
			actionInstance.addRule(ruleFactory.createRule(ruleName));
		}

		// construct and add outcomes
		for (OutcomeParamValue param : value.getAllOutcomes()) {
			actionInstance.addOutcome(outcomeFactory.createOutcome(myOutcomes, param));
		}

		System.out.println("Adding action in factory");
		System.out.println(actionInstance);

		return (MapObjectEvent) actionInstance;

	}

}
