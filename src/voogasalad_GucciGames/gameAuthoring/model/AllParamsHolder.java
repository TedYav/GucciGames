package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ParamObjParser;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;

public class AllParamsHolder {
	private Map<String, ActionParam> myActionParams = new HashMap<String, ActionParam>();
	private Map<String, ObjectParam> myMapObjectCharParams = new HashMap<String, ObjectParam>();
	private Map<String, ObjectParam> myConditions = new HashMap<String, ObjectParam>();
	private Map<String, ObjectParam> myOutcomes = new HashMap<String, ObjectParam>();

	
	public AllParamsHolder() {
		ParamObjParser parser = new ParamObjParser();
		Set<ObjectParam> mapObjCharacteristics = parser.getMapObjChars();
		for (ObjectParam param : mapObjCharacteristics) {
			myMapObjectCharParams.put(param.getName(), param);
		}
		Set<ActionParam> actions = parser.getActions();
		for (ActionParam action : actions) {
			myActionParams.put(action.getName(), action);
		}
		Set<ObjectParam> conditions = parser.getConditions();
		for (ObjectParam param : conditions) {
			myConditions.put(param.getName(), param);
		}
		Set<ObjectParam> outcomes = parser.getOutcomes();
		for (ObjectParam param : outcomes) {
			myOutcomes.put(param.getName(), param);
		}
		
	}

	public List<ObjectParam> getSelectedMapObjCharParams(List<String> selectedChar) {
		return myMapObjectCharParams.values().stream().filter(c -> selectedChar.contains(c.getName()))
				.collect(Collectors.toList());
	}

	public Map<String, ObjectParam> getOutcomeParams() {
		return myOutcomes;
	}
	
	public Map<String, ObjectParam> getConditionParams() {
		return myConditions;
	}
	
	public Map<String, ObjectParam> getMapObjectCharacteristicParams() {
		return myMapObjectCharParams;
	}
	
	public Map<String, ActionParam> getActionParams() {
		return myActionParams;
	}

	public Map<String, RuleParams> getRulesParams() {
		return null;
	}

	
	
}
