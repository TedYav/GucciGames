package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public interface MenuBarElement {
    public ResourceBundle myBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.menubar.menubar");

	public Node returnNodeToDraw();

}
