package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;

public class SplashScreen implements WindowComponent {
	
	private ImageView myImage;
	private Text myText;
	private StackPane myPane;
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.Splash");
	
	public SplashScreen(String text){
		this(text, "");
	}
	
	public SplashScreen(String text, String image){
		myPane = new StackPane();
		initializeImage(image);
		initializeText(text);
		animateText();
	}
	
	private void initializeText(String text){
		myText = new Text();
		myText.setText(text);
		myText.setFont(Font.font (myConfig.getString("Font"), Integer.parseInt(myConfig.getString("Size"))));
		myText.setFill(new Color(Double.parseDouble(myConfig.getString("R"))
				, Double.parseDouble(myConfig.getString("G"))
				, Double.parseDouble(myConfig.getString("B")) 
				, Double.parseDouble(myConfig.getString("A"))));
		myPane.getChildren().add(myText);
	}
	
	private void animateText(){
		FadeTransition f = new FadeTransition(Duration.millis(Double.parseDouble(myConfig.getString("Duration"))), myText);
		f.setFromValue(1.0);
		f.setToValue(0.0);
		f.setCycleCount(Timeline.INDEFINITE);
		f.setAutoReverse(true);
		f.play();
	}
	
	private void initializeImage(String image) {
		if(!image.isEmpty()){
			Image splash = new Image(image);
			myImage = new ImageView(splash);
			myPane.getChildren().add(myImage);
		}
	}

	@Override
	public Parent getParent() {
		return myPane;
	}

}
