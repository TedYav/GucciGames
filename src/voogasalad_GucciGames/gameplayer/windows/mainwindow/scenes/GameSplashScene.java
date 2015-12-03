package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad_GucciGames.gameData.GameDataException;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.LoaderComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class GameSplashScene extends GameScene {
	
	public GameSplashScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
	}

	@Override
	public void load() {
		System.out.println("LOADED " + getName());
		SplashScreen splash = new SplashScreen(this, getManager().getController(), myConfig.getBaseBundleName());
		splash.setText(getManager().getController().getEngine().getGameName());
		
		LoaderComponent loader = new LoaderComponent(this, getManager().getController(), myConfig.getBaseBundleName());
		splash.addChild(loader);
		
		myScene = new Scene(splash.getParent());
		
		loader.setDisplay("Loading Game Engine.");
		
		loadScene(myScene);
		try {
			myManager.getLoader().loadSelectedGame();
		} catch (GameDataException e1) {
			myManager.getLoader().loadDefault();
		}
	}

    @Override
    public void refresh () {
        // TODO Auto-generated method stub
        
    }
}
