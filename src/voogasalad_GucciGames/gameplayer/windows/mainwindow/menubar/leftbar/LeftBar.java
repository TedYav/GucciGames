package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.leftbar;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.controller.GameEngineInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;

public class LeftBar extends WindowComponent{
    private VBox container;
    private double spacing = 5;
    public LeftBar (GameScene scene, GameEngineInterface game) {
        super(scene, game);
        container = new VBox(spacing);
    }

    @Override
    public Parent getParent () {
        return container;
    }

}
