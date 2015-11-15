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

public class InitialScene extends GameScene {

	private String mySplash;
	private Scene myScene;
	private StackPane myPane;
	private Timeline myTimeline;
	private Text myText;
	
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
		animateText();
		myScene = new Scene(myPane);
		myScene.addEventHandler(KeyEvent.KEY_PRESSED, (e)->myManager.sceneFinished());
		loadScene(myScene);
	}
	
	private void initializeText(){
		myText = new Text();
		myText.setText("Press any key to continue...");
		myText.setFont(Font.font ("Verdana", 72));
		myText.setFill(Color.WHITE);
		myPane.getChildren().add(myText);
	}
	
	private void animateText(){
		FadeTransition f = new FadeTransition(Duration.millis(Double.parseDouble(myConfig.getString("FrameDuration"))), myText);
		f.setFromValue(1.0);
		f.setToValue(0.0);
		f.setCycleCount(Timeline.INDEFINITE);
		f.setAutoReverse(true);
		f.play();
	}
	
	private void initializePane() {
		myPane = new StackPane();
		System.out.println(mySplash);
		Image splash = new Image(mySplash);
		ImageView splashview = new ImageView(splash);
		myPane.getChildren().add(splashview);
	}
}
