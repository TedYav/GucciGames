package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

public class DefaultOwnerItem extends Menu {
	DefaultOwnerItem(String name, AGuiGaeController controller) {
		super(name);
		ToggleGroup group = new ToggleGroup();
		RadioMenuItem p0 = new RadioMenuItem("Player 0");
		p0.setUserData(0);
		p0.setToggleGroup(group);
		p0.setSelected(true);

		RadioMenuItem p1 = new RadioMenuItem("Player 1");
		p1.setUserData(1);
		p1.setToggleGroup(group);

		RadioMenuItem p2 = new RadioMenuItem("Player 2");
		p2.setUserData(2);
		p2.setToggleGroup(group);

		getItems().addAll(p0, p1, p2);
		group.selectedToggleProperty().addListener((ob, oldV, newV) -> {
			if (group.getSelectedToggle() != null) {
				controller.setDefaultOwner((int) newV.getUserData());
			}
		});
	}
}
