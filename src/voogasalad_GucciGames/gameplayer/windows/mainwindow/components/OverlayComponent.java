package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.ResourceBundle;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class OverlayComponent extends WindowComponent {
	
	private WindowComponent myChild;
	private ResourceBundle myConfig = PlayerConfig.load("components.OverlayComponent");

	private boolean showing = false;
	
	public OverlayComponent(GameScene scene, GameControllerInterface controller, WindowComponent child) {
		super(scene, controller);
		myChild = child;
		setParent(myChild.getParent());
		getGameScene().getScene().setOnKeyPressed(e -> handleKey(e));
	}

	private void handleKey(KeyEvent e) {
		switch(e.getCode()){
		case ESCAPE:
			if(showing){
				getGameScene().removeOverlay();			}
			else{
				getGameScene().addOverlay(this, Double.parseDouble(myConfig.getString("Opacity")));
			}
			showing = !showing;
		}
	}

}
