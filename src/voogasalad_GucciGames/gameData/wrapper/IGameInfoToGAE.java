package voogasalad_GucciGames.gameData.wrapper;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.MainGameEngine;

public interface IGameInfoToGAE {
	
	public void addLevel(String gameName, boolean chooseable);
	public void addLevel(String gameName, boolean chooseable, int nextLevelId                                   );
	
//	public void editLevel(String gameName);
	
	public void deleteLevel(String gameName);
	
	public void swapLevelIDs(String first, String second);
	
	public String getName();
	
	public int getLevelID(String name);
	
	public Map<Integer, String>getLevelsMap();
	
	public List<String> getChoosableLevels();

	public void setEngine(String gameName, MainGameEngine engine);   
	
}
