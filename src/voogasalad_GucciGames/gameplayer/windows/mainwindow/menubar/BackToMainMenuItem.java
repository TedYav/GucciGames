package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar;

import javafx.scene.control.MenuItem;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class BackToMainMenuItem extends MenuItem{
    BackToMainMenuItem(String name, GameControllerInterface controller) {
        super(name);
        setOnAction(e -> {
                //TODO
        });
}
}
