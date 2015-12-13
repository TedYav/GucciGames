package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;

public interface IGameProperties {

	public List<ObjParam> getAllMapObjCharParams();

	public List<ObjParam> getSelectedMapObjCharParams(List<String> selectedChar);

	public List<ObjParam> getAllPlayerCharParams();

	public List<ObjParam> getSelectedPlayerCharParams(List<String> selectedChar);

	public List<ObjParam> getAllOutcomes();

	public List<ObjParam> getSelectedOutcomes(List<String> selectedOutcomes);

	public List<ObjParam> getAllConditions();

	public List<ObjParam> getSelectedConditions(List<String> selectedConditions);

	public List<RuleParams> getAllRules();

	public List<ActionParam> getAllActions();

	public List<RuleParams> getSelectedRules(List<String> selectedRules);

	// public void addCharacteristic(ObjParam param, MapObjectType type);

	public void addPlayerCharacteristic(int playerID, ObjParamValue param);

	public void addMapObjectCharacteristic(ObjParamValue param);

	public void addActionParamValue(ActionParamsValue param);

	public void addGroovyCharacteristic(GCharParam param);

	public void addGroovyAction(GActionParams param);

	// public void addCharParamValue(ObjParamValue param);

}
