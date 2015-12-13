package voogasalad_GucciGames.gameAuthoring.gui.util;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WelcomeScreen extends Pane{
	public WelcomeScreen() {
		super();
		ImageView img = new ImageView("http://p1.pichost.me/i/19/1421181.jpg");
		img.fitHeightProperty().bind(heightProperty());
		img.fitWidthProperty().bind(widthProperty());
		getChildren().add(img);
		Text text = new Text("Use menu to start editing a new game");
		text.setFont(new Font(40));
		text.setFill(Color.ALICEBLUE);
		getChildren().add(text);
		text.setX(100);
		text.yProperty().bind(heightProperty().divide(2));

	}
}
