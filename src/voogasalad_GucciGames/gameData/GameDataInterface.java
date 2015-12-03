package voogasalad_GucciGames.gameData;

import java.util.List;

public interface GameDataInterface {

	public GameInfo loadGame(String name);
	
	public List<String> getAvailableGames();
	
	public GameInfo loadGameFromFile(String path) throws GameDataException;

}
