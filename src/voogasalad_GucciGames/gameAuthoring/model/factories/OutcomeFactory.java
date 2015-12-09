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
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

public class OutcomeFactory {
	
	private Map<String, ObjParam> myConditions = new HashMap<String, ObjParam>();
	
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
    	ParamObjParser parser = new ParamObjParser();
    	Set<ObjParam> conditions = parser.getConditions();
    	for (ObjParam param: conditions){
    		myConditions.put(param.getName(), param);
    	}
		
	}
	
	public Outcome createOutcome(Map<String, ObjParam> params, OutcomeParamValue value) throws ClassNotFoundException, NoSuchMethodException, 
	SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, 
	InvocationTargetException {
		
		// constructs empty Action object
		System.out.println(value.getName());
		System.out.println(prop.getProperty(value.getName()));
		
		Class<Outcome> outcome = (Class<Outcome>) Class.forName(prop.getProperty(value.getName()));
		Constructor<Outcome> outcomeConstructor = outcome.getDeclaredConstructor();
		Outcome outcomeInstance = outcomeConstructor.newInstance();

		ObjParam outcomeParam = params.get(value.getName());

		// construct and add conditions
		for(ObjParamValue param: value.getConditions()) {
			outcomeInstance.addCondition((Conditions)conditionFactory.create(myConditions, param));
		}

		System.err.println("OUTCOME FACTORY NOT IMPLEMENTED");
			
		
		return (Outcome)outcomeInstance;
		
	}

}
