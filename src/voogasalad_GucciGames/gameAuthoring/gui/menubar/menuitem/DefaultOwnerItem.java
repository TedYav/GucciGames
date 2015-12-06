package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

public class DefaultOwnerItem extends Menu{
	DefaultOwnerItem(String name, AGuiGaeController controller) {
		super(name);
		ToggleGroup group = new ToggleGroup();
		RadioMenuItem on = new RadioMenuItem("Player 0");
		on.setUserData(0);
		on.setToggleGroup(group);
		on.setSelected(true);
		
		RadioMenuItem off = new RadioMenuItem("Player 1");
		off.setUserData(1);
		off.setToggleGroup(group);
		
		getItems().addAll(on,off);
		group.selectedToggleProperty().addListener((ob,oldV,newV)->{
			if (group.getSelectedToggle() != null) {
				controller.setDefaultOwner((int)newV.getUserData());
			}
		});
	}
}
