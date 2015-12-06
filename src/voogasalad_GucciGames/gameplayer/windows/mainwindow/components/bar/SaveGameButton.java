package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar;

import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class SaveGameButton extends DisplayComponent{
    private Button saveGame;
    private ResourceBundle myBundle=PlayerConfig.load("components.SaveGameButton");
    public SaveGameButton(GameScene scene, GameControllerInterface controller) {
        super(scene,controller);
        saveGame=new Button(myBundle.getString("savegame"));
        saveGame.setOnMouseClicked(e->{
            //getScene().getManager().loadScene("MainMenuScene");
        });
    }

    @Override
    public Parent getParent() {
        return saveGame;
    }

}
