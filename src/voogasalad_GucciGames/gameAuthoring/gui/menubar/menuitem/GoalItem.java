package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

class GoalItem extends MenuItem{
	GoalItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
		setOnAction(e -> {
			//controller.saveToXML();
			//TODO
		});
	}
}
