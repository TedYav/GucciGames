package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;

public interface IGameProperties {
	
	
	public List<ObjParam> getAllMapObjCharParams();
	
	public List<ObjParam> getSelectedMapObjCharParams(List<String> selectedChar);
	
	public List<ObjParam> getAllPlayerCharParams();
	
	public List<ObjParam> getSelectedPlayerCharParams(List<String> selectedChar);
	
	public List<ObjParam> getAllOutcomes();
	
	public List<ObjParam> getSelectedOutcomes(List<String> selectedOutcomes);

	public List<ObjParam> getAllConditions();
	
	public List<ObjParam> getSelectedConditions(List<String> selectedConditions);
	
	public void addActionParam(ActionParams param);
	
	public void addAction(ActionParams params, MapObjectType type);
	
	public void addCharacteristic(ObjParam param, MapObjectType type);
	
	
}
