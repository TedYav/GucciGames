package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class SplashScreen extends WindowComponent {
	
	private ImageView myImage;
	private Text myText;
	private StackPane myPane;
	private VBox myTextLayer;
	
	private ResourceBundle myMainConfig = PlayerConfig.load("components.Splash");
	private ResourceBundle myConfig;
	
	private double mySpeed;
	private double myDuration;
	
	/*
	 * TODO: allow window components to take config in constructor
	 */
	
	public SplashScreen(GameScene scene, GameControllerInterface controller, String config) {
		super(scene, controller);
		myPane = new StackPane();
		setParent(myPane);
		myPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		myConfig = ResourceBundle.getBundle(config);
		loadConfig();
		initializeImage(myConfig.getString("Image"));
		initializeText(myConfig.getString("Text"));
		animateText();
		setAdvanceTimer();
	}
	
	public void setText(String text){
		myText.setText(text);
	}
	
	private void loadConfig() {
		mySpeed = getValue("Speed");
		myDuration = getValue("Duration");
	}

	private double getValue(String key) {
		return myConfig.containsKey(key) ? Double.parseDouble(myConfig.getString(key)) : Double.parseDouble(myMainConfig.getString(key));
	}

	private void initializeText(String text){
		myTextLayer = new VBox();
		myText = new Text();
		myText.setText(text);
		myText.getStyleClass().addAll(
				myConfig.keySet().stream()
				.filter((s) -> s.contains("Class"))
				.map(s->myConfig.getString(s))
				.collect(Collectors.toList()));
		myTextLayer.getChildren().add(myText);
		myTextLayer.setMaxWidth(myText.getBoundsInLocal().getWidth());
		myTextLayer.setMaxHeight(myText.getBoundsInLocal().getHeight());
		myPane.getChildren().add(myTextLayer);
	}
	
	private void animateText(){
		FadeTransition f = new FadeTransition(Duration.millis(mySpeed), myText);
		f.setFromValue(1.0);
		f.setToValue(0.0);
		f.setCycleCount(Timeline.INDEFINITE);
		f.setAutoReverse(true);
		f.play();
	}
	
	private void initializeImage(String image) {
		if(!image.isEmpty()){
			Image splash = getController().getResource().getImage(image);
			myImage = new ImageView(splash);
			myImage.setFitHeight(Screen.getPrimary().getBounds().getHeight());
			myImage.setPreserveRatio(true);
			myPane.getChildren().add(myImage);
		}else{
			myPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}
	
	private void setAdvanceTimer(){
		if(myDuration > 0){
			Timeline timeline = new Timeline(new KeyFrame(
			        Duration.millis(myDuration),
			        e -> getGameScene().getManager().sceneFinished()));
			timeline.play();
		}
		
	}

	public void addChild(WindowComponent component) {
		myTextLayer.getChildren().add(component.getParent());
		System.out.println("ADDING CHILD");
		myTextLayer.setMaxHeight( myTextLayer.getMaxHeight() + component.getParent().getBoundsInLocal().getHeight() );
	}

}