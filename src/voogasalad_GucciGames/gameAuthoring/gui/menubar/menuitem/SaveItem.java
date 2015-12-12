package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

class SaveItem extends MenuItem {
	SaveItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		setOnAction(e -> {
			controller.saveToXML();
			// FileChooser f = new FileChooser();
			// f.setInitialDirectory(new
			// File("./src/voogasalad_GucciGames/gameData"));
			// f.getExtensionFilters().add(new ExtensionFilter("XML", "*.xml"));
			// f.setInitialFileName("gaeengine.xml");
			// File file = f.showSaveDialog(controller.getStage());
			// if(file!=null)
			// controller.saveToXML(file);
		});
	}
}
