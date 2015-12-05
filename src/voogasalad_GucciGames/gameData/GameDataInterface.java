package voogasalad_GucciGames.gameData;

import java.util.List;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

public interface GameDataInterface {

	public GameEngine loadGame(String name);
	
	public List<String> getAvailableGames();
	
	public GameEngine loadGameFromFile(String path) throws GameDataException;

	public GameEngine loadDefault();

}
