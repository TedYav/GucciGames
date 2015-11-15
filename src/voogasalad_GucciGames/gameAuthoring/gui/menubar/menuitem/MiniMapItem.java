package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

public class MiniMapItem extends Menu{
	MiniMapItem(String name, IGuiGaeController controller) {
		super(name);
		ToggleGroup groupShape = new ToggleGroup();
		RadioMenuItem on = new RadioMenuItem("On");
		on.setUserData("On");
		on.setToggleGroup(groupShape);
		
		RadioMenuItem off = new RadioMenuItem("Off");
		off.setUserData("Off");
		off.setToggleGroup(groupShape);
		
		off.setSelected(true);
		getItems().addAll(on,off);
		groupShape.selectedToggleProperty().addListener((ob,oldV,newV)->{
			if (groupShape.getSelectedToggle() != null) {
				//TODO
				//myGui.myCellType = (String)newV.getUserData();
				//myGui.reset();
			}
		});
	}
}
