package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import java.io.File;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

class SaveItem extends MenuItem {
	SaveItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		setOnAction(e -> {
			FileChooser f = new FileChooser();
			f.setInitialDirectory(new File("./src/voogasalad_GucciGames/gameData"));
			f.getExtensionFilters().add(new ExtensionFilter("XML", "*.xml"));
			f.setInitialFileName("gaeengine.xml");
			File file = f.showSaveDialog(controller.getStage());
			if(file!=null)
				controller.saveToXML(file);
		});
	}
}
