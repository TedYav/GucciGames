package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class LoadGameButton extends DisplayComponent{
    private Button loadGame;
    private ResourceBundle myBundle=PlayerConfig.load("components.LoadGameButton");
    public LoadGameButton(GameScene scene, GameControllerInterface controller) {
        super(scene,controller);
        loadGame=new Button(myBundle.getString("loadgame"));
        loadGame.setOnMouseClicked(e->{
            getGameScene().getManager().loadScene("LoadGameScene");
        });
    }

    @Override
    public Parent getParent() {
        return loadGame;
    }

}
