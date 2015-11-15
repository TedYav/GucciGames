package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

class OpenItem extends MenuItem{
	OpenItem(String name, IGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
		setOnAction(e -> {
			//controller.saveToXML();
			//TODO
		});
	}
}
