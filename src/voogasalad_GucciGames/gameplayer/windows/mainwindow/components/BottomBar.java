package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class BottomBar extends WindowSideComponent{
    private HBox container;
    private double spacing = 5;
    private ResourceBundle myBundle=PlayerConfig.load("components.Bar");
    private ResourceBundle myCssBundle = PlayerConfig.load(myBundle.getString("cssclass"));
    
    public BottomBar (GameScene scene, GameControllerInterface controller, List<DisplayComponent> components) {
        super(scene, controller, components);
        container = new HBox(spacing);
        initializeData();
    }

	@Override
    protected void initializeData() {
        for (DisplayComponent d: getMyComponents()) {
            container.getChildren().add(d.getParent());
        }
        container.getStyleClass().add(myCssBundle.getString("BottomHBox"));
        container.setPrefHeight(Double.parseDouble(myCssBundle.getString("bottomprefheight")));
    }

    @Override
    public Parent getParent () {
        return container;
    }
}
