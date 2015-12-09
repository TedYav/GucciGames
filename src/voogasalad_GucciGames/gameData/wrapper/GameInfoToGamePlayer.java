package voogasalad_GucciGames.gameData.wrapper;

import java.util.List;
import java.util.Map;
import voogasalad_GucciGames.gameEngine.IGameLevelToGamePlayer;

public interface GameInfoToGamePlayer {

	public String getGameName();
		
	public List<String> getGuiComponents(String location);
	
	public void setGuiComponents(String location, List<String> components);
	
	public Map<String, IGameLevelToGamePlayer> getLevels();
	
	public GameEngineToGamePlayerInterface getEngineInterface();

    void setGuiData (GuiData gui);
}
