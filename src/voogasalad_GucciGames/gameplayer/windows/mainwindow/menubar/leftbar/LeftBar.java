package voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.leftbar;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.controller.GameEngineInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;

public class LeftBar extends WindowComponent implements MiniDisplayComponent{
    private VBox container;
    private double spacing = 5;
    ResourceBundle myBundle;
    public LeftBar (GameScene scene, GameEngineInterface game, ResourceBundle bundle) {
        super(scene, game);
        container = new VBox(spacing);
        myBundle=bundle;
        initializeData();
    }
    private void initializeData() {
        DisplayMapObjectDetails objectDetails = new DisplayMapObjectDetails();
        container.getChildren().add(objectDetails.getNodeToDraw());
        container.getStyleClass().add(myBundle.getString("LeftVBox"));
    }

    @Override
    public Parent getParent () {
        return container;
    }
    public Node getNodeToDraw() {
        return container;
    }

}
