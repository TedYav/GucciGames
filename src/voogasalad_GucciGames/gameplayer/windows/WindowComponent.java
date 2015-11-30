package voogasalad_GucciGames.gameplayer.windows;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.ListChangeListener;
import javafx.scene.Parent;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public abstract class WindowComponent{

    private GameScene myScene;
    private GameControllerInterface myController;

    public WindowComponent(GameScene scene, GameControllerInterface controller){
        setMyScene(scene);
        setMyController(controller);
    }

    public abstract Parent getParent();

    public GameScene getMyScene () {
        return myScene;
    }

    public void setMyScene (GameScene myScene) {
        this.myScene = myScene;
    }

    public GameControllerInterface getMyController () {
        return myController;
    }

    public void setMyController (GameControllerInterface myController) {
        this.myController = myController;
    }

}
