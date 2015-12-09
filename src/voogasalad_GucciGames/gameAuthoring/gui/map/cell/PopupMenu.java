package voogasalad_GucciGames.gameAuthoring.gui.map.cell;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

public class PopupMenu extends ContextMenu {

	private final AGuiGaeController myController;
	private final Menu myOwnerMenu;
	private final Cell myCell;

	public PopupMenu(AGuiGaeController controller, Cell cell) {
		myController = controller;
		myCell = cell;
		MenuItem item1 = new MenuItem("Edit");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Edit");
			}
		});
		MenuItem item2 = new MenuItem("Remove");
		item2.setOnAction(e -> cell.clear());

		myOwnerMenu = new Menu("Owner");
		update();
		getItems().addAll(item1, item2, myOwnerMenu);
	}

	protected void update() {
		myOwnerMenu.getItems().clear();
		ToggleGroup group = new ToggleGroup();
		myController.getAllPlayersId().forEach((id, name) -> {
			RadioMenuItem item = new RadioMenuItem(name);
			item.setUserData(id);
			item.setToggleGroup(group);
			myOwnerMenu.getItems().add(item);
		});
		myOwnerMenu.setDisable(myOwnerMenu.getItems().size() == 0);

		group.selectedToggleProperty().addListener((ob, oldV, newV) -> {
			if (group.getSelectedToggle() != null) {
				myCell.setOwner((int) newV.getUserData());
			}
		});
	}
}
