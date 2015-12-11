package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class BackgroundComponent extends WindowComponent {

	private Pane myPane;
	
	public BackgroundComponent(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		myPane = new Pane();
		setParent(myPane);
	}
	
	
}
