package voogasalad_GucciGames.gameAuthoring.gui.menubar;

import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem.MenuItemLoader;

public class GAEMenuBar extends MenuBar{
	
	private List<Menu> myMenus;
	
	public GAEMenuBar(AGuiGaeController controller){
		MenuItemLoader loader = new MenuItemLoader();
		try {
			myMenus = loader.load(controller);
		} catch (Exception e) {
			controller.throwException(e);
		}
		getMenus().addAll(myMenus);
		myMenus.forEach(m->m.setDisable(true));
		myMenus.get(0).setDisable(false);
		controller.getHasGameProperty().addListener((c,oV,nV)->{
			myMenus.forEach(m->m.setDisable(!nV.booleanValue()));
			myMenus.get(0).setDisable(false);
		});
	}

}
