package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameData.GameDataInterface;

public class FileItem implements GameMenu {

    private ComboBox<String> myDropdown;
    private ResourceBundle myCssBundle = ResourceBundle.getBundle(myBundle.getString("cssclass"));

    public FileItem(GameDataInterface myLoader, Stage stage){
        myDropdown = new ComboBox<String>();
        myDropdown.setValue(myBundle.getString("file"));
        myDropdown.getItems().add(myBundle.getString("load"));
        myDropdown.setOnAction(e -> {
            if(myDropdown.getValue().equals(myBundle.getString("load"))){
                openBrowser(stage);
            }
        });
        myDropdown.getStyleClass().add(myCssBundle.getString("dropdowncssclass"));
    }

    @Override
    public Node returnNodeToDraw() {
        return myDropdown;
    }

    private void openBrowser (Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(myBundle.getString("browser"));
        fileChooser.getExtensionFilters().add(new ExtensionFilter(myBundle.getString("xmldescription"),myBundle.getString("xmlextension")));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {

            }
            catch (Exception e) {
            }
        }
    }


}
