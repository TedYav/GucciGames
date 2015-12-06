package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.ResourceBundle;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class OverlayComponent extends WindowComponent {
	
	private WindowComponent myChild;
	private ResourceBundle myConfig = PlayerConfig.load("components.OverlayComponent");

	private boolean showing = false;
	private KeyCode myKeyCode;
	
	public OverlayComponent(GameScene scene, GameControllerInterface controller, WindowComponent child) {
		super(scene, controller);
		myChild = child;
		setParent(myChild.getParent());
	}

	private void handleKey(KeyEvent e) {
		if(e.getCode().equals(myKeyCode)){
			if(showing){
				hide();			}
			else{
				show();
			}
		}
	}

	public void hide() {
		getGameScene().removeOverlay();
		showing = false;
	}
	
	public void activateKeyHandler(KeyCode code){
		myKeyCode = code;
		getGameScene().getScene().setOnKeyPressed(e -> handleKey(e));
	}
	
	public void show(){
		getGameScene().addOverlay(this, Double.parseDouble(myConfig.getString("Opacity")));
		showing = true;
	}

}
