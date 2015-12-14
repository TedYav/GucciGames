package voogasalad_GucciGames.gameAuthoring;

import java.util.Map;

import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.model.IGameProperties;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import voogasalad_GucciGames.helpers.GameResourceManagerToGAE;

public interface IDialogGaeController {
	public void createCustomType(MapObjectType object);

	public void deleteMapObjectType(MapObjectType object);

	public ObservableList<MapObjectType> getImmutableTileTypes();

	public ObservableList<MapObjectType> getImmutableUnitTypes();

	public ObservableList<MapObjectType> getImmutableStructureTypes();

	public void setNumberOfPlayers(int n);

	public int getNumberOfPlayers();

	public Map<Integer, String> getAllPlayersId();

	public void addPlayerToList(String name, int id);

	public void savePlayer(PlayerParams params);

	public void saveGameSetting(GameSettingParams params);

	// Getters
	// public List<ObjParam> getAllMapObjCharParams();
	//
	// public List<ObjParam> getSelectedMapObjCharParams(List<String>
	// selectedChar);
	//
	// public List<ObjParam> getAllPlayerCharParams();
	//
	// public List<ObjParam> getSelectedPlayerCharParams(List<String>
	// selectedChar);
	//
	// public List<ObjParam> getAllOutcomes();
	//
	// public List<ObjParam> getSelectedOutcomes(List<String> selectedOutcomes);
	//
	// public List<ObjParam> getAllConditions();
	//
	// public List<ObjParam> getSelectedConditions(List<String>
	// selectedConditions);

	// condense into this method

	public IGameProperties getPropertiesInterface();

	public GameResourceManagerToGAE getResourceManager();

	public void throwException(Exception e);

}
