package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.GameSettingDialog;

class GameItem extends MenuItem{
	GameItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
		setOnAction(e -> {
			GameSettingDialog  gameSettingDialog = new GameSettingDialog(controller);
			gameSettingDialog.showAndWait();
			//controller.saveToXML();
			//TODO
		});
	}
}
