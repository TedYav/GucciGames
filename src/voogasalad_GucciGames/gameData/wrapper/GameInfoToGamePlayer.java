package voogasalad_GucciGames.gameData.wrapper;

import java.util.List;
import java.util.Map;

public interface GameInfoToGamePlayer {

	public String getGameName();
	
	public Map<Integer, IGameLevelToGamePlayer> getLevels();
	
	public List<String> getLeftComponents();
	
	public List<String> getRightComponents();

    public List<String> getBottomComponents ();
	
}
