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

public class SelectGameTypeScene extends GameMenuScene {
	
	public SelectGameTypeScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
    protected Map<String, MenuAction> buildOptionMap() {
		Map<String, MenuAction> optionMap = new LinkedHashMap<>();
		optionMap.put("Local Game", () -> myManager.sceneFinished());
		optionMap.put("Network Game", () -> myManager.loadScene("NetworkConfigScene"));
		optionMap.put("Back", () -> myManager.loadScene(myConfig.getString("PrevScene")));
		return optionMap;
	}
}
