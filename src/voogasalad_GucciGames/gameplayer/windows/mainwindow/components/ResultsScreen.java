package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class ResultsScreen extends WindowComponent {

	private Map<String, String> myInformation;
	private String myTitle;
	protected Text myTitleText;
	
	private StackPane myParent;
	private VBox myTextBox;
	
	private List<Text> myText;
	
	private int flashIndex=1;
	private int maxFlashIndex;
	
	private ResourceBundle myConfig = PlayerConfig.load("components.ResultsScreen");
	
	public ResultsScreen(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
	}
	
	public ResultsScreen(GameScene scene, GameControllerInterface controller, String title, Map<String, String> information) {
		super(scene, controller);
		initialize(title, information);
	}
	
	public void initialize(String title, Map<String, String> information){
		myInformation = information;
		myTitle = title;
		myText = new ArrayList<>();
		initializePanes();
		sizeBox();
		drawTitle();
		drawInformation();
		drawEnd();
		startAnimation();
	}
	
	private void startAnimation() {
		maxFlashIndex = Integer.parseInt(myConfig.getString("FlashColors"));
		Timeline flashTimeline = new Timeline(new KeyFrame(Duration.millis(Double.parseDouble(myConfig.getString("FlashRate"))),e -> flash(myTitleText)));
		flashTimeline.setCycleCount(Timeline.INDEFINITE);
		flashTimeline.play();
	}

	private void flash(Text text) {
		flashIndex++;
		text.setFill(Color.web(PlayerConfig.getResourceNumber(myConfig, "FlashColor", flashIndex%maxFlashIndex)));
	}

	private void drawEnd() {
		Text t = new Text(myConfig.getString("EndText"));
		t.getStyleClass().addAll("gametext", "results-end");
		myTextBox.getChildren().add(t);
		myText.add(t);
	}

	private void drawInformation() {
		myInformation.keySet().stream()
			.forEach(s -> drawText(s));
	}
	
	private void drawText(String s) {
		Text field = new Text(s);
		field.getStyleClass().addAll("gametext", "results-field");
		myText.add(field);
		myTextBox.getChildren().add(field);
		Text value = new Text(myInformation.get(s));
		value.getStyleClass().addAll("gametext", "results-value");
		myText.add(value);
		myTextBox.getChildren().add(value);
	}

	private void sizeBox() {
		double maxWidth = 0.0;
		double maxHeight = 0.0;
		double minHeight = Double.parseDouble(myConfig.getString("MinHeight"));
		for(Text t : myText){
			maxWidth = (t.getBoundsInLocal().getWidth()>maxWidth)?t.getBoundsInLocal().getWidth():maxWidth;
			maxHeight += t.getBoundsInLocal().getHeight();
		}
		myTextBox.setMaxHeight(Double.max(maxHeight, minHeight));
		myTextBox.setMaxWidth(maxWidth);
	}

	private void initializePanes() {
		myParent = new StackPane();
		myTextBox = new VBox();
		myParent.getChildren().addAll(myTextBox);
		setParent(myParent);
	}
	
	private void drawTitle() {
		myTitleText = new Text(myTitle);
		myTextBox.getChildren().add(myTitleText);
	}

	
	
	
}
