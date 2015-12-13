package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class PlayerTurnDisplay extends DisplayComponent {
	private VBox parent;
	private Label turn;
	private ResourceBundle myBundle = PlayerConfig.load("components.PlayerTurnDisplay");

	public PlayerTurnDisplay(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		turn = new Label(myBundle.getString("turndisplay") + controller.getEngine().getGameParameters().whoseTurn());
		parent = new VBox();
		parent.getChildren().add(turn);
		VBox.setVgrow(turn, Priority.ALWAYS);
	}

	@Override
	public Parent getParent() {
		return parent;
	}

	@Override
	public void updateDisplay() {
		turn.setText(myBundle.getString("turndisplay") + getController().getEngine().getGameParameters().whoseTurn());
	}

}
