package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;

public class FileMenu implements GameMenu {

	
	private ComboBox<String> myDropdown;
	
	public FileMenu(GameDataInterface myLoader, Stage stage){
		myDropdown = new ComboBox<String>();
		myDropdown.setValue(myBundle.getString("file"));
		myDropdown.getItems().add("load");
		myDropdown.setOnAction(e -> {if(myDropdown.getValue().equals("load")){
		openBrowser(stage);
			
		}
		});
		myDropdown.getStyleClass().add(myBundle.getString("dropdowncssclass"));
	}
	
	@Override
	public Node returnNodeToDraw() {
		return myDropdown;
	}

	    private void openBrowser (Stage stage) {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("browser");
	        fileChooser.getExtensionFilters().add(new ExtensionFilter("xml file",".xml"));
//	                .addAll(new ExtensionFilter(getTextResources().getString("imageextensionlabel"),
//	                                            getTextResources()
//	                                                    .getString("imageextensions")
//	                                                    .split(getTextResources()
//	                                                            .getString("imageextensiondelimiter"))));
	        File selectedFile = fileChooser.showOpenDialog(stage);
	        if (selectedFile != null) {
	            try {
	            }
	            catch (Exception e) {
	            }
	        }
	    }
	
	
}
