package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class MainMenuScene extends GameMenuScene {
	
	public MainMenuScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
	}

	@Override
	protected Map<String, MenuAction> buildOptionMap() {
		Map<String, MenuAction> options = new LinkedHashMap<>();
		options.put("New Game", () -> myManager.sceneFinished());
		options.put("Load Game", () -> myManager.loadScene("LoadGameScene"));
		options.put("View High Scores", () -> myManager.loadScene("HighScoresScene"));
		options.put("Reload", () -> { myManager.getLoader().loadGame(myManager.getController().getEngine().getGameName()); 
										myManager.loadScene("GameSplashScene"); });
		options.put("Quit", () -> myManager.loadScene("SelectGameScene"));
		return options;
	}
}
