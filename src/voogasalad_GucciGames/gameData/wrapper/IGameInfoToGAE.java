package voogasalad_GucciGames.gameData.wrapper;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.GameLevelEngine;

public interface IGameInfoToGAE {
	

	
//	public void editLevel(String gameName);
	
	
	
	public String getGameName();
		
	public Map<String, IGameLevelToGamePlayer> getLevelsMap();
	
	public List<String> getChoosableLevels();

	public void setEngine(String gameName, GameLevelEngine engine);  
	
	public void addTransferableCharacteristic(String name);
	
}
