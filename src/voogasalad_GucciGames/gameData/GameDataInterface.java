package voogasalad_GucciGames.gameData;

import java.util.List;

import voogasalad_GucciGames.gameData.wrapper.GameInfo;

public interface GameDataInterface {

	public GameInfo loadGame(String name);
	
	public List<String> getAvailableGames();
	
	public GameInfo loadGameFromFile(String path) throws GameDataException;

	public GameInfo loadDefault();

}
