package voogasalad_GucciGames.gameplayer.scenes.concrete;

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
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuScreen;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public abstract class GameMenuScene extends GameScene {
	
	protected MenuScreen myMenu;
	private String myTitle;
	
	public GameMenuScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
		myTitle = myConfig.getString("MenuTitle");
	}

	@Override
	public void load() {
		System.out.println("LOADED " + getName());
		
		myMenu = new MenuScreen(this, myManager.getController(), buildOptionMap(), myTitle);
		myScene = new Scene(myMenu.getParent());
		loadScene(myScene);
	}

    protected abstract Map<String, MenuAction> buildOptionMap();

	@Override
    public void refresh () {
        // TODO Auto-generated method stub
        
    }
}
