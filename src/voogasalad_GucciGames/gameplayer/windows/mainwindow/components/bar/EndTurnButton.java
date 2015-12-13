package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class EndTurnButton extends DisplayComponent {
	private Button endTurn;
	private ResourceBundle myBundle = PlayerConfig.load("components.EndTurnButton");

	public EndTurnButton(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		endTurn = new Button(myBundle.getString("endturn"));
		endTurn.setOnMouseClicked(e -> {
			controller.endTurn();
		});
	}

	@Override
	public void updateDisplay() {
		endTurn.setText(myBundle.getString("endturn"));
	}

	@Override
	public Parent getParent() {
		return endTurn;
	}

}
