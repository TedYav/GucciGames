package voogasalad_GucciGames.gameplayer.windows;

import javafx.scene.Parent;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;

public abstract class WindowComponent {

	protected GameScene myScene;
	protected GameEngineToGamePlayerInterface myGame;
	
	public WindowComponent(GameScene scene, GameEngineToGamePlayerInterface game){
		myScene = scene;
		myGame = game;
	}
	
	public abstract Parent getParent();
	
}
