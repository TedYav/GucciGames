package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;

public class GameMenu implements MenuBarElement {
	
	
	private ComboBox<String> myDropdown;
	
	public GameMenu(){
		// Change to resource file
		myDropdown = new ComboBox<String>();
		myDropdown.setValue(myBundle.getString("game"));
		String exit = myBundle.getString("exit");
		myDropdown.getItems().add(exit);
		myDropdown.setOnAction(e -> {if(myDropdown.getValue().equals(exit)){
			// TODO
			System.out.println("exiting");
		}
		});
		String save = myBundle.getString("save");
		myDropdown.getItems().add(save);
		myDropdown.setOnAction(e -> {if(myDropdown.getValue().equals(save)){
			// TODO
			System.out.println("saving game");
		}
		});
		
		myDropdown.getStyleClass().add(myBundle.getString("dropdowncssclass"));
	}
	
	@Override
	public Node returnNodeToDraw() {
		return myDropdown;
	}

}
