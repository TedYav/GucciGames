package voogasalad_GucciGames.gameAuthoring;

import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.StructureParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.TileParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.UnitParams;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;

public interface IDialogGaeController {
	public void createCustomMapObject(ObjectProperty p);
	
	public void setNumberOfPlayers(int n);
	
	public int getNumberOfPlayers();
	
	public Map<Integer, String> getAllPlayersId();
	
	public void addPlayerToList(String name, int id);
	
	public void savePlayer(PlayerParams params);
	
	public void saveGameSetting(GameSettingParams params);
	
	public void setTileParams(TileParams params);
	
	public void setUnitParams(UnitParams params);
	
	public void setStructureParams(StructureParams params);

}
