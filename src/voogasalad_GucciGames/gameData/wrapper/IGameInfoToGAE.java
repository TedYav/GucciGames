package voogasalad_GucciGames.gameData.wrapper;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.GameLevelEngine;
import voogasalad_GucciGames.gameEngine.IGameLevelToGamePlayer;

public interface IGameInfoToGAE {
	

	
//	public void editLevel(String gameName);
	
	
	
	public String getGameName();
		
	public Map<String, IGameLevelToGamePlayer> getLevelsMap();
	
	public List<String> getChoosableLevels();

	public void setLevel(String gameName, GameLevelEngine engine);   
	public void setEngine(String gameName, GameLevelEngine engine);  
	
	public void addTransferableCharacteristic(String name);
	
}
