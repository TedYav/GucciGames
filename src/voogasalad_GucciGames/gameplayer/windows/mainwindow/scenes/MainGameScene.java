package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import voogasalad_GucciGames.gameplayer.controller.GameEngineInterface;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.GameWindowInterface;

public class MainGameScene extends GameScene{

	private GameEngineInterface myGame;
	
	public MainGameScene(GameSceneManager manager, GameWindowInterface window, String config) {
		super(manager, window, config);
	}
	
	private void loadGameData(){
		// do a bunch of stuff with myLoader
		myGame = myManager.getLoader().getCurrentGame();
	}

	@Override
	public void load() {
		//STEPS UPON LOAD:
		/*
		 * 1. Load Game Data
		 * 2. Show Splash Screen
		 * 3. Show Main Menu / Level Chooser
		 * 4. Show Game Screen
		 * 5. Show Result of Game
		 * 6. Go to Next Level or Go to Main Menu
		 */
		showSplash();
		
	}
	
	private void showSplash(){
		
	}
	
	
}
