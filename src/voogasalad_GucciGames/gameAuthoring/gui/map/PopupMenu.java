package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

public class PopupMenu extends ContextMenu {
	
	private AGuiGaeController myController;
	private Menu myOwnerMenu;
	
	public PopupMenu(AGuiGaeController controller,Cell cell) {
		myController = controller;
		MenuItem item1 = new MenuItem("Edit");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Edit");
			}
		});
		MenuItem item2 = new MenuItem("Remove");
		item2.setOnAction(e -> cell.removeFromMap());
		
		myOwnerMenu = new Menu("Owner");
		update();
		getItems().addAll(item1,item2,myOwnerMenu);
	}
	
	protected void update(){
		myOwnerMenu.getItems().clear();
		ToggleGroup group = new ToggleGroup();
		myController.getAllPlayersId().forEach((id,name)->{
			RadioMenuItem item = new RadioMenuItem(name);
			item.setUserData(id);
			item.setToggleGroup(group);
			myOwnerMenu.getItems().add(item);
		});
		
		group.selectedToggleProperty().addListener((ob, oldV, newV) -> {
			if (group.getSelectedToggle() != null) {
				int id = (int)newV.getUserData();
				//cell.setPlayerID(id);
			}
		});
	}
}
