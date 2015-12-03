package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;

public class LeftBar extends WindowSideComponent{
    private VBox container;
    private double spacing = 5;
    private ResourceBundle myBundle=PlayerConfig.load("components.Bar");
    private ResourceBundle myCssBundle = PlayerConfig.load(myBundle.getString("cssclass"));

    public LeftBar (GameScene scene, GameControllerInterface controller, List<DisplayComponent> components) {
        super(scene, controller, components);
        container = new VBox(spacing);
        setParent(container);
        initializeData();
    }

    @Override
    protected void initializeData() {
        for (DisplayComponent d: getMyComponents()) {
            container.getChildren().add(d.getParent());
        }
        container.getStyleClass().add(myCssBundle.getString("LeftVBox"));
        container.setPrefWidth(Double.parseDouble(myCssBundle.getString("leftprefwidth")));
    }

}
