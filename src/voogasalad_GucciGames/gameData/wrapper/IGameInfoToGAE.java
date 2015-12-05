package voogasalad_GucciGames.gameData.wrapper;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.MainGameEngine;

public interface IGameInfoToGAE {
	
	public GameLevel addLevel(String levelName);
	
//	public void editLevel(String gameName);
	
	public void deleteLevel(int levelID);
	
	public void swapLevels(int first, int second);
	
	public String getGameName();
		
	public Map<Integer, GameLevel> getLevelsMap();
	
	
}
