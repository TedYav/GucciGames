package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.SettingsDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.MainDialog;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

class SideBarMenu extends ContextMenu {
	private final AGuiGaeController myController;
	private MapObjectType myType;
	private final String myTypeName;

	SideBarMenu(AGuiGaeController controller, String typeName) {
		myController = controller;
		myTypeName = typeName;
		MenuItem item1 = new MenuItem("Edit");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				MainDialog dialog = new MainDialog(controller, myType);
				//SettingsDialog dialog = new SettingsDialog(controller, myType);
				dialog.show();
			}
		});
		MenuItem item2 = new MenuItem("Duplicate");
		item2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				controller.createCustomType(myType.makeCopy(), myTypeName);
			}
		});
		MenuItem item3 = new MenuItem("Remove");
		item3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				controller.deleteMapObjectType(myType, myTypeName);
			}
		});

		getItems().addAll(item1, item2, item3);
	}

	public void setCurrType(MapObjectType type) {
		myType = type;
	}
}
