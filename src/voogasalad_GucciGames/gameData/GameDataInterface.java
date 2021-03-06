package voogasalad_GucciGames.gameData;

import java.util.List;

import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GamePlayerSave;

public interface GameDataInterface {

	public GameInfo loadGame(String name);

	public void saveGame(GamePlayerSave game);

	public List<String> getAvailableGames();

	public GameInfo loadGameFromFile(String path) throws GameDataException;

	public GameInfo loadDefault();

	public List<String> getAvailableSaves(String gameName);

	public GamePlayerSave loadSave(String saveName, String gameName);

}
