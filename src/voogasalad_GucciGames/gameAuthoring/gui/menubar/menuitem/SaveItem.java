package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

class SaveItem extends MenuItem {
	SaveItem(String name, IGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		setOnAction(e -> {
			controller.saveToXML();
		});
	}
}
