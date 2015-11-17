package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;

public class FileMenu implements MenuBarElement {

	
	private ComboBox<String> myDropdown;
	
	public FileMenu(GameDataInterface myLoader){
		// Change to resource file
		myDropdown = new ComboBox<String>();
		myDropdown.setValue(myBundle.getString("file"));
		String load = myBundle.getString("load");
		myDropdown.getItems().add(load);
		myDropdown.setOnAction(e -> {if(myDropdown.getValue().equals(load)){
		myLoader.loadGames();
			System.out.println("loading game");
		}
		});
		String save = myBundle.getString("save");
		myDropdown.getItems().add(save);
		myDropdown.setOnAction(e -> {if(myDropdown.getValue().equals(save)){
		myLoader.loadGames();
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
