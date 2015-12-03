package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.WindowComponent;

public class TopBar extends WindowComponent {
	private GameControllerInterface myController;

	public TopBar(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Parent getParent() {
		MenuBar menu = new MenuBar();
		MenuLoader loader = new MenuLoader();
		menu.getMenus().addAll(loader.load(myController));
		return menu;
	}
	
	

}
