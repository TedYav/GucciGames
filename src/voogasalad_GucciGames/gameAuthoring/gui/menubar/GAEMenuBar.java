package voogasalad_GucciGames.gameAuthoring.gui.menubar;

import javafx.scene.control.MenuBar;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem.MenuItemLoader;

public class GAEMenuBar extends MenuBar{
	
	public GAEMenuBar(AGuiGaeController controller) throws Exception{
		MenuItemLoader loader = new MenuItemLoader();
		getMenus().addAll(loader.load(controller));
	}

}
