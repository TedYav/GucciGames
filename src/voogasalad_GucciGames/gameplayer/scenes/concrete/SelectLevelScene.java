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
		Map<String, IGameLevelToGamePlayer> levels = getManager().getController().getGame().getLevels();
		levels.keySet().stream()
		    .forEach( s -> System.out.println(levels.get(s).getLevelName()));
		levels.keySet().stream()
			.map( name -> levels.get(name))
			.filter( level -> level.isMyChoosability() )
			.forEach( level -> options.put(level.getLevelName(), () -> loadLevel(level)));
		options.put("Back", () -> myManager.loadScene(myConfig.getString("PrevScene")));
		return options;
	}

	private void loadLevel(IGameLevelToGamePlayer level) {
	    System.out.println("loading"+level.getLevelName());
		getManager().getController().loadLevel(level.getLevelName());
		myManager.sceneFinished();
	}
}
