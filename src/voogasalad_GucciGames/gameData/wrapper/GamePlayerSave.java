package voogasalad_GucciGames.gameData.wrapper;

public class GamePlayerSave {
	private GameInfo myGame;
	private String myCurrentLevel;

	public GamePlayerSave(GameInfo game, String currentLevel) {
		myGame = game;
		setCurrentLevel(currentLevel);
	}

	public GameInfo getInfo() {
		return myGame;
	}

	public void setInfo(GameInfo game) {
		myGame = game;
	}

	public String getCurrentLevel() {
		return myCurrentLevel;
	}

	public void setCurrentLevel(String myCurrentLevel) {
		this.myCurrentLevel = myCurrentLevel;
	}
}
