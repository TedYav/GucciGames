package voogasalad_GucciGames.gameData.wrapper;

public abstract class GameInfo implements GameInfoToGamePlayer {
	GameEngine myEngine;
	GuiData myGuiData;
	
	public GameInfo(){
		myEngine = new GameEngine();
		myGuiData = new GuiData();
	};
	
	
	
	

}
