package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.ListChangeListener;
import javafx.scene.Parent;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;

public abstract class WindowComponent{

    private GameScene myScene;
    private GameControllerInterface myController;
    private Parent myParent; 

	public WindowComponent(GameScene scene, GameControllerInterface controller){
		setScene(scene);
        setController(controller);
    }
    
    public Parent getParent() {
		return myParent;
	}

	public void setParent(Parent myParent) {
		this.myParent = myParent;
	}
    
    public GameScene getScene () {
        return myScene;
    }

    public void setScene (GameScene myScene) {
        this.myScene = myScene;
    }

    public GameControllerInterface getController () {
        return myController;
    }

    public void setController (GameControllerInterface myController) {
        this.myController = myController;
    }

}
