package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;

public class GameMenuBar {

    /**
     * Takes a list of menu objects, implementing Menu interface
     * Instantiates each of them with JavaFX
     * @param menus
     */
    private ToolBar menu;
    private ResourceBundle myBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.menubar.menubar");
    private ResourceBundle myCssBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.scenes.CssClasses");


    public GameMenuBar(List<GameMenu> menus){
        menu = new ToolBar();
        for (GameMenu m: menus) {
            menu.getItems().add(m.returnNodeToDraw());
        }
        menu.getStyleClass().add(myCssBundle.getString("menubarcssclass"));
    }

    public Node returnToolbar() {
        return menu;
    }

}
