package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import javafx.scene.control.ComboBox;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;

public class FileMenu implements GameMenu {

	
	ComboBox<String> myDropdown;
	
	public FileMenu(GameDataInterface myLoader){
		myDropdown = new ComboBox<String>();
		myDropdown.setOnInputMethodTextChanged(e -> {if(myDropdown.getPromptText().equals("load")){
		myLoader.loadGames();
			
		}
		});
	}
	
	@Override
	public ComboBox<String> returnMenuDropdown() {
		return null;
	}

	
	
	
}
