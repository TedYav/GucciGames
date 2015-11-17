package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;

public class FileMenu implements GameMenu {

	
	private ComboBox<String> myDropdown;
	
	public FileMenu(GameDataInterface myLoader){
		myDropdown = new ComboBox<String>();
		myDropdown.setValue(myBundle.getString("file"));
		myDropdown.getItems().add("load");
		myDropdown.setOnInputMethodTextChanged(e -> {if(myDropdown.getPromptText().equals("load")){
		myLoader.loadGames();
			System.out.println("asf");
		}
		});
		myDropdown.getStyleClass().add(myBundle.getString("dropdowncssclass"));
	}
	
	@Override
	public Node returnNodeToDraw() {
		return myDropdown;
	}

	
	
	
}
