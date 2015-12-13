package voogasalad_GucciGames.gameplayer.scenes.concrete;

import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;

public class CreditsScene extends GameScene {
	
	public CreditsScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
	}

	@Override
	public void load() {
		System.out.println("LOADED " + getName());
		myManager.sceneFinished();
	}

    @Override
    public void refresh () {
        // TODO Auto-generated method stub
        
    }
}
