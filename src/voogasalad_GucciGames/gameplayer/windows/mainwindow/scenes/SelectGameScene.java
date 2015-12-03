package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuScreen;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class SelectGameScene extends GameMenuScene {
		
	public SelectGameScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
    protected Map<String, MenuAction> buildOptionMap() {
		Map<String, MenuAction> optionMap = new HashMap<>();
		for(String s : myManager.getLoader().getAvailableGames()){
			optionMap.put(s, () -> selectGame(s));
		}
		return optionMap;
	}
    
    private void selectGame(String gameName){
    	myManager.getLoader().selectGame(gameName);
    	myManager.sceneFinished();
    }
}
