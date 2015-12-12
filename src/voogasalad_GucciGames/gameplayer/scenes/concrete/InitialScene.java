package voogasalad_GucciGames.gameplayer.scenes.concrete;

import javafx.scene.input.KeyEvent;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class InitialScene extends GameScene {

	public InitialScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}

	@Override
	protected void readConfig() {
		super.readConfig();
	}

	@Override
	public void load() {
		SplashScreen splash = new SplashScreen(this, getManager().getController(), myConfig.getBaseBundleName());
		myScene.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> myManager.sceneFinished());
		loadParent(splash.getParent());
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
}
