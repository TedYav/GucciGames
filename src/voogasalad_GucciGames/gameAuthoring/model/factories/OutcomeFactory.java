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
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.model.AllParamsHolder;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;

public class OutcomeFactory {

	InputStream inputStream;
	private Properties prop;

	private static final String PATH_TO_OUTCOME_PROPERTIES = "outcomesPath.properties";

	private ConditionFactory conditionFactory = new ConditionFactory();

	public OutcomeFactory() {
		inputStream = getClass().getResourceAsStream(PATH_TO_OUTCOME_PROPERTIES);
		prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Outcome createOutcome(AllParamsHolder params, OutcomeParamValue value)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<Outcome> outcome = (Class<Outcome>) Class.forName(prop.getProperty(value.getName()));
		Constructor<Outcome> outcomeConstructor = outcome.getDeclaredConstructor();
		Outcome outcomeInstance = outcomeConstructor.newInstance();

		ObjectParam outcomeParam = params.getOutcomeParams().get(value.getName());

		// construct and add conditions
		for (ObjParamValue param : value.getConditions()) {
			outcomeInstance.addCondition((Conditions) conditionFactory.create(params.getConditionParams(), param));
		}

		return (Outcome) outcomeInstance;

	}

}
