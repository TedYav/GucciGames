package voogasalad_GucciGames.gameData.wrapper;

public interface IGameLevelToGamePlayer {

	public boolean isMyChoosability();

	public String getLevelName();

	public String getNextLevel();

	public boolean hasLevelEnded();

	// public GameEngineToGamePlayerInterface getGameEngine();

}
