package voogasalad_GucciGames.gameAuthoring;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public interface IDialogGaeController {
	public void createCustomType(MapObjectType object, String type);
	
	public void setNumberOfPlayers(int n);
	
	public int getNumberOfPlayers();
	
	public Map<Integer, String> getAllPlayersId();
	
	public void addPlayerToList(String name, int id);
	
	public void savePlayer(PlayerParams params);
	
	public void saveGameSetting(GameSettingParams params);
	
	// Getters
	public List<ObjParam> getAllMapObjCharParams();
	
	public List<ObjParam> getSelectedMapObjCharParams(List<String> selectedChar);
	
	public List<ObjParam> getAllPlayerCharParams();
	
	public List<ObjParam> getSelectedPlayerCharParams(List<String> selectedChar);
	
	public List<ObjParam> getAllOutcomes();
	
	public List<ObjParam> getSelectedOutcomes(List<String> selectedOutcomes);

	public List<ObjParam> getAllConditions();
	
	public List<ObjParam> getSelectedConditions(List<String> selectedConditions);
	
	// Add
	// action name, list of rules, list of outcomes
	public void addActionParam(ActionParams param);
	
	//public void add
	
	
	
	

}
