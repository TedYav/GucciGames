package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.SettingsDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.UnitSettingsDialog;

class SideBarMenu extends ContextMenu {
	private AGuiGaeController myController;
	SideBarMenu(AGuiGaeController controller){
		myController = controller;
		MenuItem item1 = new MenuItem("Edit");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Edit");
				SettingsDialog dialog = new SettingsDialog(controller);
				dialog.show();
				//UnitSettingsDialog dialog = new UnitSettingsDialog(controller);
				//dialog.show();
			}
		});
		MenuItem item2 = new MenuItem("Duplicate");
		item2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Duplicate");
			}
		});
		MenuItem item3 = new MenuItem("Remove");
		item3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Remove");
			}
		});
		
		getItems().addAll(item1,item2,item3);
	}
}
