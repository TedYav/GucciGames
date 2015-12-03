package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.CustomGPlayerDialog;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCombination;

public class CustomizableGPlayerItem extends Menu{
	
	CustomizableGPlayerItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
		setOnAction(e -> {
			CustomGPlayerDialog  customGamePlayerDialog = new CustomGPlayerDialog((IDialogGaeController)controller,(IGuiGaeController)controller);
			customGamePlayerDialog.showCustomGPlayerDialog();
			//controller.saveToXML();
			//TODO
		});
	}
}
