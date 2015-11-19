package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

public class MiniMapItem extends Menu{
	MiniMapItem(String name, AGuiGaeController controller) {
		super(name);
		ToggleGroup group = new ToggleGroup();
		RadioMenuItem on = new RadioMenuItem("On");
		on.setUserData("On");
		on.setToggleGroup(group);
		
		RadioMenuItem off = new RadioMenuItem("Off");
		off.setUserData("Off");
		off.setToggleGroup(group);
		
		off.setSelected(true);
		getItems().addAll(on,off);
		group.selectedToggleProperty().addListener((ob,oldV,newV)->{
			if (group.getSelectedToggle() != null) {
				//TODO
				//myGui.myCellType = (String)newV.getUserData();
				//myGui.reset();
			}
		});
	}
}
