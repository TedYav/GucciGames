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

public class NetworkConfigScene extends GameMenuScene {
	
	public NetworkConfigScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
    protected Map<String, MenuAction> buildOptionMap() {
		Map<String, MenuAction> optionMap = new LinkedHashMap<>();
		optionMap.put("Host Game", () -> {getManager().getController().getEngine().beHost();
											getManager().sceneFinished();});
		optionMap.put("Join Game", () -> {getManager().getController().getEngine().beClient("10.190.209.220");
		getManager().sceneFinished();;});
		optionMap.put("Back", () -> myManager.loadScene(myConfig.getString("PrevScene")));
		return optionMap;
	}
}
