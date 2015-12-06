package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.GroovyDialog;

class GroovyItem extends MenuItem {
	GroovyItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
		setOnAction(e -> {
			//TODO
			GroovyDialog dialog = new GroovyDialog();
			dialog.show();
			
		});
	}
}
