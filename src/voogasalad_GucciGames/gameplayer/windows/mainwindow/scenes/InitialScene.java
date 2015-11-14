package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;

public class InitialScene extends GameScene {

	String mySplash;
	Scene myScene;
	StackPane myPane;
	
	public InitialScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
		mySplash = myConfig.getString("SplashImage");
	}

	@Override
	public void load() {
		initializePane();
		initializeText();
		myScene = new Scene(myPane);
		myScene.addEventHandler(KeyEvent.KEY_PRESSED, (e)->showMenu());
		loadScene(myScene);
	}
	
	private void initializeText(){
//		Text t = new Text();
//		t.setText("Press any key to continue...");
//		t.setFont(Font.font ("Verdana", 20));
//		t.setFill(Color.WHITE);
//		myPane.getChildren().add(t);
	}
	
	

	private void initializePane() {
		myPane = new StackPane();
		Image splash = new Image(mySplash);
		ImageView splashview = new ImageView(splash);
		myPane.getChildren().add(splashview);
	}
	
	private void showMenu(){
		// for now, just jump to main scene
		myManager.sceneFinished();
	}

}
