package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.CustomGPlayerDialog;

public class CustomizableGPlayerItem extends MenuItem {

	CustomizableGPlayerItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
		setOnAction(e -> {
			CustomGPlayerDialog<Object> customGamePlayerDialog = new CustomGPlayerDialog<>(
					(IDialogGaeController) controller, (IGuiGaeController) controller);
			customGamePlayerDialog.show();
		});
	}
}
