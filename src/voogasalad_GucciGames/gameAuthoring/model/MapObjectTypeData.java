package voogasalad_GucciGames.gameAuthoring.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ParamObjParser;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjType;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameAuthoring.model.factories.ActionFactory;
import voogasalad_GucciGames.gameAuthoring.model.factories.MapCharacteristicFactory;
import voogasalad_GucciGames.gameAuthoring.model.factories.GroovyActionFactory;
import voogasalad_GucciGames.gameAuthoring.model.factories.GroovyMapChars;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

public class MapObjectTypeData {

	private MapCharacteristicFactory mapCharacteristicFactory = new MapCharacteristicFactory();
	private GroovyMapChars groovyMapCharacteristicFactory = new GroovyMapChars();
	private GroovyActionFactory groovyActionFactory = new GroovyActionFactory();
	private ActionFactory actionFactory = new ActionFactory();

	private Map<String, GActionParams> myGroovyActionParams = new HashMap<String, GActionParams>();
	private Map<String, GCharParam> myGroovyMapObjectCharParams = new HashMap<String, GCharParam>();

	private AllParamsHolder myParamsHolder;

	public MapObjectTypeData() {
		myParamsHolder = new AllParamsHolder();
	}

	public void addMapObjectCharacteristic(ObjParamValue param) {
		if (myGroovyMapObjectCharParams.containsKey(param.getName())) {
			try {
				param.getMapObjectType().addCharacteristic((AMapObjectCharacteristic) groovyMapCharacteristicFactory
						.create(myGroovyMapObjectCharParams, param));
			} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				System.err.println("FAILED TO ADD GROOVY CHARACTERISTIC");
			}

		} else {
			param.getMapObjectType().addCharacteristic(
					(AMapObjectCharacteristic) mapCharacteristicFactory.create(myParamsHolder.getMapObjectCharacteristicParams(), param));
		}
	}

		public List<ObjectParam> getAllMapObjCharParams() {
		return new ArrayList<>(myParamsHolder.getMapObjectCharacteristicParams().values());
	}

	public List<ObjectParam> getSelectedMapObjCharParams(List<String> selectedChar) {
		return myParamsHolder.getSelectedMapObjCharParams(selectedChar);
	}

	public void addActionParamValue(ActionParamsValue param) {
		if (myGroovyActionParams.containsKey(param.getName())) {
			param.getMapObjectType()
					.addAction((MapObjectEvent) groovyActionFactory.create(myGroovyActionParams, param));

		} else {
			param.getMapObjectType().addAction(actionFactory.createAction(myParamsHolder, param));
		}
	}

	public Map<String, GActionParams> getGroovyActionParams() {
		return myGroovyActionParams;
	}

	public Map<String, GCharParam> getGroovyMapObjectCharParams() {
		return myGroovyMapObjectCharParams;
	}

	public void addGroovyCharacteristic(GCharParam param) {
		myGroovyMapObjectCharParams.put(param.getName(), param);
		ObjectParam charparam = new ObjectParam(param.getName(), ObjType.MAP_CHAR, 0);
		param.getAllParams().keySet().stream().forEach(key -> {
			charparam.addParam(param.getAllParams().get(key), key);
		});
		myParamsHolder.getMapObjectCharacteristicParams().put(param.getName(), charparam);

	}

	public void addGroovyAction(GActionParams param) {
		myGroovyActionParams.put(param.getName(), param);
		ActionParam actionParam = new ActionParam(param.getName());
		myParamsHolder.getActionParams().put(param.getName(), actionParam);
	}

	public List<ObjectParam> getSelectedOutcomes(List<String> selectedOutcomes) {
		return myParamsHolder.getOutcomeParams().values().stream().filter(c -> selectedOutcomes.contains(c.getName()))
				.collect(Collectors.toList());
	}

	public List<ObjectParam> getAllConditions() {
		return new ArrayList<>(myParamsHolder.getConditionParams().values());
	}

	public List<ObjectParam> getSelectedConditions(List<String> selectedConditions) {
		return myParamsHolder.getConditionParams().values().stream()
				.filter(c -> selectedConditions.contains(c.getName())).collect(Collectors.toList());
	}

	public List<ActionParam> getAllActions() {
		return new ArrayList<>(myParamsHolder.getActionParams().values());
	}

	public List<ObjectParam> getAllOutcomes() {
		return new ArrayList<>(myParamsHolder.getOutcomeParams().values());
	}

	public List<RuleParams> getSelectedRules(List<String> selectedRules) {
		return myParamsHolder.getRulesParams().values().stream()
				.filter(c -> selectedRules.contains(c.getName())).collect(Collectors.toList());
	}

	public List<RuleParams> getAllRules() {
		return new ArrayList<>(myParamsHolder.getRulesParams().values());
	}

}
