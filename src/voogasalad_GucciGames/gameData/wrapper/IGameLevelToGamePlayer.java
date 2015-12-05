package voogasalad_GucciGames.gameData.wrapper;

public interface IGameLevelToGamePlayer {
	
	public int getID();
	
	@Deprecated
	public int getNextLevel();
	
	public boolean isChoosable();
	
	public String getLevelName();

}
