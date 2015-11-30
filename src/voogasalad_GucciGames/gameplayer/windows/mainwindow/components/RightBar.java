package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;

public class RightBar extends WindowSideComponent{
    private VBox container;
    private double spacing = 5;
    private ResourceBundle myBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.RightBar");
    private ResourceBundle myCssBundle = ResourceBundle.getBundle(myBundle.getString("cssclass"));

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