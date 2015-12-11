package voogasalad_GucciGames.gameplayer.scenes.concrete;

import voogasalad_GucciGames.gameData.GameDataException;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.LoaderComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class GameSplashScene extends GameScene {
	
	public GameSplashScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
	}

	@Override
	public void load() {
		System.out.println("LOADED " + getName());
		SplashScreen splash = new SplashScreen(this, getManager().getController(), myConfig.getBaseBundleName());
                System.out.println(getManager());
                System.out.println(getManager().getController().getGame());
                splash.setText(getManager().getController().getGame().getGameName());
		
		LoaderComponent loader = new LoaderComponent(this, getManager().getController(), myConfig.getBaseBundleName());
		splash.addChild(loader);
						
		loadParent(splash.getParent());
		try {
			myManager.getLoader().loadSelectedGame();
		} catch (GameDataException e1) {
			e1.printStackTrace();
			myManager.getLoader().loadDefault();
		}
	}

    @Override
    public void refresh () {
        // TODO Auto-generated method stub
        
    }
}
