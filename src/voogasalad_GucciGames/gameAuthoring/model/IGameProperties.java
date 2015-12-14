package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

public interface IGameProperties {

	public List<ObjectParam> getAllMapObjCharParams();

	public List<ObjectParam> getSelectedMapObjCharParams(List<String> selectedChar);

	public List<ObjectParam> getAllPlayerCharParams();

	public List<ObjectParam> getSelectedPlayerCharParams(List<String> selectedChar);

	public List<ObjectParam> getAllOutcomes();

	public List<ObjectParam> getSelectedOutcomes(List<String> selectedOutcomes);

	public List<ObjectParam> getAllConditions();

	public List<ObjectParam> getSelectedConditions(List<String> selectedConditions);

	public List<RuleParams> getAllRules();

	public List<ActionParam> getAllActions();

	public List<RuleParams> getSelectedRules(List<String> selectedRules);

	// public void addCharacteristic(ObjParam param, MapObjectType type);

	public void addPlayerCharacteristic(int playerID, ObjParamValue param);

	public void addMapObjectCharacteristic(ObjParamValue param);

	public void addActionParamValue(ActionParamsValue param);

	public void addGroovyCharacteristic(GCharParam param);

	public void addGroovyAction(GActionParams param);

	Map<Integer, GamePlayerPerson> getMapOfPlayers();

	// public void addCharParamValue(ObjParamValue param);

}
