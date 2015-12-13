package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import javafx.scene.Parent;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

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
    
    public void setParent(Parent parent){
    	setParent(parent, true);
    }

	public void setParent(Parent parent, boolean applyCSS) {
		myParent = parent;
		if(applyCSS){
			styleParent();
		}
	}
    
    private void styleParent() {
    	myParent.getStyleClass().add("gametext");
	}

	public GameScene getGameScene () {
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
