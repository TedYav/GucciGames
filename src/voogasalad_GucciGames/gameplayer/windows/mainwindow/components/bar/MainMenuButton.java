package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class MainMenuButton extends DisplayComponent{
    private Button endTurn;
    private ResourceBundle myBundle=PlayerConfig.load("components.MainMenuButton");
    public MainMenuButton(GameScene scene, GameControllerInterface controller) {
        super(scene,controller);
        endTurn=new Button(myBundle.getString("mainmenu"));
        endTurn.setOnMouseClicked(e->{
            getGameScene().getManager().loadScene("MainMenuScene");
        });
    }

    @Override
    public Parent getParent() {
        return endTurn;
    }

}
