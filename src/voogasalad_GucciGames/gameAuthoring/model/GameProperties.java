package voogasalad_GucciGames.gameAuthoring.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import voogasalad_GucciGames.gameAuthoring.model.factories.PlayerCharacteristicFactory;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

public class GameProperties implements IGameProperties {
	private TypeData myTypeData;
	private PlayerData myPlayerData;
	private MapObjectTypeData myMapObjectTypeData;

	public GameProperties() {
		myTypeData = new TypeData();
		myPlayerData = new PlayerData();
		myMapObjectTypeData = new MapObjectTypeData();
	}
	
	public void addMapObjectType(MapObjectType type) {
		myTypeData.addMapObjectType(type);
	}

	public ObservableList<MapObjectType> getImmutableTileTypes() {
		return myTypeData.getImmutableTileTypes();
	}

	public ObservableList<MapObjectType> getImmutableUnitTypes() {
		return myTypeData.getImmutableUnitTypes();
	}

	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return myTypeData.getImmutableStructureTypes();
	}

	@Override
	public List<ObjectParam> getAllMapObjCharParams() {
		return myMapObjectTypeData.getAllMapObjCharParams();
	}

	@Override
	public List<ObjectParam> getSelectedMapObjCharParams(List<String> selectedChar) {
		return myMapObjectTypeData.getSelectedMapObjCharParams(selectedChar);
	}

	@Override
	public List<ObjectParam> getAllPlayerCharParams() {
		return myPlayerData.getAllPlayerCharParams();
	}

	@Override
	public List<ObjectParam> getSelectedPlayerCharParams(List<String> selectedChar) {
		return myPlayerData.getSelectedPlayerCharParams(selectedChar);
	}

	@Override
	public List<ObjectParam> getAllOutcomes() {
		return myMapObjectTypeData.getAllOutcomes();
	}

	@Override
	public List<ObjectParam> getSelectedOutcomes(List<String> selectedOutcomes) {
		return myMapObjectTypeData.getSelectedOutcomes(selectedOutcomes);
	}

	@Override
	public List<ObjectParam> getAllConditions() {
		return myMapObjectTypeData.getAllConditions();
	}

	@Override
	public List<ObjectParam> getSelectedConditions(List<String> selectedConditions) {
		return myMapObjectTypeData.getSelectedConditions(selectedConditions);
	}

	@Override
	public List<RuleParams> getAllRules() {
		return myMapObjectTypeData.getAllRules();
	}

	@Override
	public List<RuleParams> getSelectedRules(List<String> selectedRules) {
		return myMapObjectTypeData.getSelectedRules(selectedRules);
	}

	@Override
	public void addPlayerCharacteristic(int playerID, ObjParamValue param) {
		myPlayerData.addPlayerCharacteristic(playerID, param);
	}

	@Override
	public void addMapObjectCharacteristic(ObjParamValue param) {
		myMapObjectTypeData.addMapObjectCharacteristic(param);
	}

	@Override
	public void addActionParamValue(ActionParamsValue param) {
		myMapObjectTypeData.addActionParamValue(param);
	}
	
	@Override
	public List<ActionParam> getAllActions() {
		return myMapObjectTypeData.getAllActions();
	}

	@Override
	public Map<Integer, GamePlayerPerson> getMapOfPlayers() {
		return myPlayerData.getMapOfPlayers();
	}

	@Override
	public void addGroovyCharacteristic(GCharParam param) {
		myMapObjectTypeData.addGroovyCharacteristic(param);
	}

	@Override
	public void addGroovyAction(GActionParams param) {
		myMapObjectTypeData.addGroovyAction(param);
	}

	public void setNumberOfPlayers(int n) {
		myPlayerData.initNumberOfPlayers(n);
	}

	public int getNumberOfPlayers() {
		return myPlayerData.getNumberOfPlayers();
	}

	public Map<String, GActionParams> getGroovyActionParams() {
		return myMapObjectTypeData.getGroovyActionParams();
	}

	public Map<String, GCharParam> getGroovyMapObjectCharParams() {
		return myMapObjectTypeData.getGroovyMapObjectCharParams();
	}

	public List<MapObjectType> getAllMapObjectTypes() {
		return myTypeData.getAllMapObjectTypes();
	}

	public void cleanSave() {
		myPlayerData.clearObjects();
	}

	public void deleteMapObjectType(MapObjectType obj) {
		myTypeData.deleteMapObjectType(obj);
	}
}
