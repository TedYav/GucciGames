package voogasalad_GucciGames.gameplayer.windows;

import javafx.scene.Parent;
import voogasalad_GucciGames.gameplayer.controller.GameEngineInterface;

public abstract class WindowComponent {

	protected GameScene myScene;
	protected GameEngineInterface myGame;
	
	public WindowComponent(GameScene scene, GameEngineInterface game){
		myScene = scene;
		myGame = game;
	}
	
	public abstract Parent getParent();
	
}
