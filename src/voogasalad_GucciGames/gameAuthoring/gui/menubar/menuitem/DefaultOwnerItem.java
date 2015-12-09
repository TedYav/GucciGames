package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

class DefaultOwnerItem extends Menu {
	private final ToggleGroup myGroup = new ToggleGroup();
	private final AGuiGaeController myController;
	
	DefaultOwnerItem(String name, AGuiGaeController controller) {
		super(name);
		myController = controller;
		controller.getNumberOfPlayersProperty().addListener((ch,oV,nV)->update(nV.intValue()));
		myGroup.selectedToggleProperty().addListener((ob, oldV, newV) -> {
			if (myGroup.getSelectedToggle() != null) {
				controller.setDefaultOwner((int) newV.getUserData());
			}
		});
	}
	
	private void update(int n){
		myGroup.getToggles().clear();
		for(int i=0;i<n;i++){
			RadioMenuItem item = new RadioMenuItem("Player "+i);
			item.setUserData(i);
			item.setToggleGroup(myGroup);
			getItems().add(item);
			if(i == myController.getDefaultOwner())
				item.setSelected(true);
		}
	}
}
