package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.io.InputStream;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;

public class ActionFactory {

	private TypeMap typeMap = new TypeMap();
	private static final String PATH_TO_RULE_PROPERTIES = "MapObjectCharacteristicsPath.properties";
	
	private OutcomeFactory outcomeFactory = new OutcomeFactory();
	private RuleFactory ruleFactory = new RuleFactory();
		
	public ActionFactory() {
		
	}

	protected InputStream getStream() {
		return getClass().getResourceAsStream(PATH_TO_RULE_PROPERTIES);
	}
	
	public void createAction(ActionParam param) {
		
		
		
	}

}
