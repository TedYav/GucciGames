package voogasalad_GucciGames.gameData;

import java.util.List;

public interface GameDataInterface {

	public void loadGames();

	public GameInfo loadGame(String name);
	
	public List<String> getAvailableGames();
	
	public GameInfo loadGameFromFile(String path) throws GameDataException;

}
