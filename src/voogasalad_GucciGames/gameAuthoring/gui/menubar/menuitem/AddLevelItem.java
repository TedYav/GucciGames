package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.NewLevelDialog;

import java.util.Map;

import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;

class AddLevelItem extends MenuItem {

	AddLevelItem(String name, AGuiGaeController controller) {
		super(name);
		setOnAction(e -> {
			Dialog<Map<String, String>> dialog = new NewLevelDialog(controller);
			dialog.showAndWait().ifPresent(map -> {
				controller.getLevelTabPane().createNewTab(map.get("name"), Integer.parseInt(map.get("width")),
						Integer.parseInt(map.get("height")));
			});
		});
	}

}
