package voogasalad_GucciGames.gameData.wrapper;

import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;

public interface IGameLevelToGamePlayer {
		
	public boolean isMyChoosability();
	
	public String getLevelName();
	
	public String getNextLevel();
	
	public boolean hasLevelEnded();
	
	public boolean getGameOver();

	//public GameEngineToGamePlayerInterface getGameEngine();

}
