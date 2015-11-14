package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class InitialScene extends GameScene {

	private Scene myScene;
	
	public InitialScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
	}

	@Override
	public void load() {
		SplashScreen splash = new SplashScreen(myConfig.getString("Text"), myConfig.getString("SplashImage"));
		myScene = new Scene(splash.getParent());
		myScene.addEventHandler(KeyEvent.KEY_PRESSED, (e)->myManager.sceneFinished());
		loadScene(myScene);
	}
	
	
}
