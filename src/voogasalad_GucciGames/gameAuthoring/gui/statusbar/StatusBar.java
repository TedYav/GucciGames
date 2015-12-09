package voogasalad_GucciGames.gameAuthoring.gui.statusbar;

import javafx.animation.Animation.Status;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

public class StatusBar extends HBox{
	
	private Text myText;
	private Transition myTextTransition;
	
	public StatusBar(AGuiGaeController controller){
		setPrefHeight(30);
		
		setBackground(
				new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(2), getInsets())));
		myText = new Text();
		myText.setFont(Font.font("Verdana", 15));
		
		getChildren().add(myText);
		myText.setTranslateY(5);
		myTextTransition = new PauseTransition(Duration.millis(1000));
		myTextTransition.setOnFinished(f->myText.setVisible(false));
	}
	
	public void update(MouseEvent e){
		if(myTextTransition.statusProperty().get()==Status.RUNNING)
			myTextTransition.stop();
		myText.setVisible(true);
		myText.setText(String.format("%3.0f x %3.0f", e.getX(),e.getY()));
		myTextTransition.play();
	}
}
