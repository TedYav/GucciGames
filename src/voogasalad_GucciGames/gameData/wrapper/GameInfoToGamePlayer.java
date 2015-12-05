package voogasalad_GucciGames.gameData.wrapper;

import java.util.List;
import java.util.Map;

public interface GameInfoToGamePlayer {

	public String getGameName();
		
	public List<String> getGuiComponents(String location);
	
	public void setGuiComponents(String location, List<String> components);
	
}
