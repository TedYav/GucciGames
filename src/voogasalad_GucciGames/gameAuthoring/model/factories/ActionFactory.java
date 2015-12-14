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
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameAuthoring.model.AllParamsHolder;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

public class ActionFactory {
	InputStream inputStream;
	private Properties prop;

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

	}

	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_RULE_PROPERTIES);
	}

	public MapObjectEvent createAction(AllParamsHolder params, ActionParamsValue value)
			throws CreatePropertyException {
		try {
			return makeAction(params, value);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new CreatePropertyException();
		}
	}

	public MapObjectEvent makeAction(AllParamsHolder params, ActionParamsValue value)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// constructs empty Action object
		Class<MapObjectEvent> action;
		action = (Class<MapObjectEvent>) Class.forName(prop.getProperty(value.getName()));
		Constructor<MapObjectEvent> actionConstructor;
		actionConstructor = action.getDeclaredConstructor();
		MapObjectEvent actionInstance;
		actionInstance = actionConstructor.newInstance();
		
		// construct and add rules
		for (String ruleName : value.getAllRules()) {
			actionInstance.addRule(ruleFactory.createRule(ruleName));
		}
		
		// construct and add outcomes
		for (OutcomeParamValue param : value.getAllOutcomes()) {
			actionInstance.addOutcome(outcomeFactory.createOutcome(params, param));
		}
		
		return (MapObjectEvent) actionInstance;

	}

}
