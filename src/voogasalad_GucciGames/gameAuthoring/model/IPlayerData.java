package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjectParam;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

public interface IPlayerData {

	public int getNumberOfPlayers();

	public int addPlayer();

	public void addPlayerCharacteristic(int playerID, ObjParamValue param);

	public Map<Integer, GamePlayerPerson> getMapOfPlayers();

	public void clearObjects();

	public List<ObjectParam> getSelectedPlayerCharParams(List<String> selectedChar);

	public List<ObjectParam> getAllPlayerCharParams();

}
