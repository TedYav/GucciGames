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
import voogasalad_GucciGames.gameData.wrapper.IGameLevelToGamePlayer;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class SelectLevelScene extends GameMenuScene {
	
	public SelectLevelScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
	}

	@Override
	protected Map<String, MenuAction> buildOptionMap() {
		
		Map<String, MenuAction> options = new LinkedHashMap<>();
		Map<Integer, IGameLevelToGamePlayer> levels = getManager().getController().getGame().getLevels();
		levels.keySet().stream()
			.map( id -> levels.get(id))
			.filter( level -> level.isChoosable() )
			.forEach( level -> options.put(level.getLevelName(), () -> loadLevel(level)));
		options.put("Back", () -> myManager.loadScene("MainMenuScene"));
		return options;
	}

	private void loadLevel(IGameLevelToGamePlayer level) {
		getManager().getController().loadLevel(level.getID());
		myManager.sceneFinished();
	}
}
