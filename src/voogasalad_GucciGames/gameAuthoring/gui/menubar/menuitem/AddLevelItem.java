package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import javafx.scene.control.Menu;

public class AddLevelItem extends Menu{

	AddLevelItem(String name, AGuiGaeController controller) {
		super(name);
		setOnAction(e -> {
			controller.getLevelTabPane().createNewTab();
			//controller.saveToXML();
			//TODO
		});
	}
	
}
