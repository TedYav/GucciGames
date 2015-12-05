package voogasalad_GucciGames.gameData.wrapper;

import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;

public interface IGameLevelToGamePlayer {
	
	public int getID();
	
	@Deprecated
	public int getNextLevel();
	
	public boolean isChoosable();
	
	public String getLevelName();

	public GameEngineToGamePlayerInterface getGameEngine();

}
