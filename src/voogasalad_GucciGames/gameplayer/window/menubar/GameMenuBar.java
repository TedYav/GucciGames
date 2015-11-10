package voogasalad_GucciGames.gameplayer.window.menubar;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.MenuBar;

public class GameMenuBar {

	/**
	 * Takes a list of menu objects, implementing Menu interface
	 * Instantiates each of them with JavaFX
	 * @param menus
	 */
	public GameMenuBar(List<GameMenu> menus){
		
	}

	public Node returnToolbar() {
		
		
		return new MenuBar();
	}
	
}
