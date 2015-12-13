package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class LoaderComponent extends WindowComponent {

	private Pane myPane;
	private Text myText;
	private boolean initialized = false;
	private ResourceBundle myConfig;
	private Timeline myTimeline;
	private double mySpeed;

	public LoaderComponent(GameScene scene, GameControllerInterface controller, String config) {
		super(scene, controller);
		myPane = new Pane();
		setParent(myPane);
		myConfig = ResourceBundle.getBundle(config);
		myText = new Text();
		myPane.getChildren().addAll(myText);
		myTimeline = new Timeline();
		mySpeed = Double.parseDouble(myConfig.getString("LoaderSpeed"));
		myText.getStyleClass().addAll(getStyleClasses(myConfig.getString("LoaderPrefix")));
		setDisplay(myConfig.getString("LoaderText"));
	}

	/*
	 * TODO: factor this function out to the top
	 */
	private List<String> getStyleClasses(String prefix) {
		return myConfig.keySet().stream().filter((s) -> s.startsWith(prefix)).map((s) -> myConfig.getString(s))
				.collect(Collectors.toList());
	}

	public void setDisplay(String text) {
		myText.setText(text);
		animateText();
	}

	private void animateText() {
		myTimeline.stop();
		myTimeline = new Timeline(new KeyFrame(Duration.millis(mySpeed), e -> updateText()));
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.play();
	}

	private void updateText() {
		myText.setText(myText.getText() + ".");
	}

	// private void aanimateText(){
	// FadeTransition f = new FadeTransition(Duration.millis(mySpeed), myText);
	// f.setFromValue(1.0);
	// f.setToValue(0.0);
	// f.setCycleCount(Timeline.INDEFINITE);
	// f.setAutoReverse(true);
	// f.play();
	// }
	//
	// private void initializeImage(String image) {
	// if(!image.isEmpty()){
	// Image splash = new Image(image);
	// myImage = new ImageView(splash);
	// myImage.setFitHeight(Screen.getPrimary().getBounds().getHeight());
	// myImage.setPreserveRatio(true);
	// myPane.getChildren().add(myImage);
	// }
	// }
	//
	// private void setAdvanceTimer(){
	// if(myDuration > 0){
	// Timeline timeline = new Timeline(new KeyFrame(
	// Duration.millis(myDuration),
	// e -> getScene().getManager().sceneFinished()));
	// timeline.play();
	// }
	//
	// }
	//

}
