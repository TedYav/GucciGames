package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import voogasalad_GucciGames.gameplayer.config.Config;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;

public class MenuScreen extends WindowComponent {

	private StackPane myParent;
	private Pane myBackground;
	private Pane myMenu;
	
	private ResourceBundle myConfig = Config.load("components.MenuScreen");
	
	public MenuScreen(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		return null;
	}

}
