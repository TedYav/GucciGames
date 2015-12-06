package voogasalad_GucciGames.gameAuthoring;

import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;

public interface IDialogGaeController {
	public void createCustomMapObject(ObjectProperty p);
	
	public void createCustomUnitType(Map<String, String> m);
	
	public void createCustomTileType(Map<String, String> m);
	
	public void createCustomStructureType(Map<String, String> m);
	
	public void setNumberOfPlayers(int n);
	
	public int getNumberOfPlayers();
	
	public Map<Integer, String> getAllPlayersId();
	
	public void addPlayerToList(String name, int id);
	
	public void savePlayer(PlayerParams params);
	
	public void saveGameSetting(GameSettingParams params);


}
