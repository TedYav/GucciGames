package voogasalad_GucciGames.gameData.wrapper;

import java.util.List;
import java.util.Map;

public interface GameInfoToGameData {

	public String getGameName();
	
	public Map<Integer, GameLevel> getLevelsMap();
	
}
