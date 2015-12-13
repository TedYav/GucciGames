package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.Map;

import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuScreen;

public abstract class GameMenuScene extends GameScene {
	
	protected MenuScreen myMenu;
	private String myTitle;
	
	public GameMenuScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
		myTitle = myConfig.getString("MenuTitle");
	}

	@Override
	public void load() {
		System.out.println("LOADED " + getName());
		
		myMenu = new MenuScreen(this, myManager.getController(), buildOptionMap(), myTitle);
		loadParent(myMenu.getParent());
	}

    protected abstract Map<String, MenuAction> buildOptionMap();

	@Override
    public void refresh () {
        // TODO Auto-generated method stub
        
    }
}
