package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

class ExitItem extends MenuItem{
	
	ExitItem(String name, IGuiGaeController controller){
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
		setOnAction(e->{System.exit(0);});
	}
	
}
