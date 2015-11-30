package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;


public class RightBar extends WindowSideComponent{
    private VBox container;
    private double spacing = 5;
    private ResourceBundle myBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.RightBar");
    private ResourceBundle myCssBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.scenes.CssClasses");

    public RightBar(GameScene scene, GameControllerInterface controller, List<DisplayComponent> components) {
        super(scene, controller, components);
        container = new VBox(spacing);
        initializeData();
    }

    @Override
    protected void initializeData() {
        for (DisplayComponent d: getMyComponents()) {
            container.getChildren().add(d.getNodeToDraw());
        }
        container.getStyleClass().add(myCssBundle.getString("RightVBox"));
        container.setPrefWidth(Double.parseDouble(myCssBundle.getString("rightprefwidth")));
    }

    @Override
    public Parent getParent() {
        return container;
    }
}
