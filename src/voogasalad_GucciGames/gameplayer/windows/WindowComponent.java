package voogasalad_GucciGames.gameplayer.windows;

import javafx.scene.Parent;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public abstract class WindowComponent {

	protected GameScene myScene;
	protected GameControllerInterface myController;
	
	public WindowComponent(GameScene scene, GameControllerInterface controller){
		myScene = scene;
		myController = controller;
	}
	
	public abstract Parent getParent();
	
}
