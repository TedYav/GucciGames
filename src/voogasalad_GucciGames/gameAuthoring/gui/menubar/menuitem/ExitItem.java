package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

class ExitItem extends MenuItem{
	
	ExitItem(String name, AGuiGaeController controller){
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
		setOnAction(e->{System.exit(0);});
	}
	
}
