package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;

public class GameMenuBar implements DisplayComponent {

	/**
	 * Takes a list of menu objects, implementing Menu interface
	 * Instantiates each of them with JavaFX
	 * @param menus
	 */
    private ToolBar menu;
    private ResourceBundle myBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.menubar.menubar");

	public GameMenuBar() {
	    List<MenuBarElement> listOfGameMenus = new ArrayList<MenuBarElement>();
	    //TODO: create for properties file?
	    FileMenu file = new FileMenu(null);
	    listOfGameMenus.add(file);
	    GameMenu game = new GameMenu();
		menu = new ToolBar();
		listOfGameMenus.stream().forEach(m -> menu.getItems().add(m.returnNodeToDraw()));
		menu.getStyleClass().add(myBundle.getString("menubarcssclass"));
	}

	public Node returnToolbar() {
		return menu;
	}

	@Override
	public Node getNodeToDraw() {
		return menu;
	}
	
}
