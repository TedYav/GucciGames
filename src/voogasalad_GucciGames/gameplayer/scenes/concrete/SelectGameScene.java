package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameData.GameDataException;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;

public class SelectGameScene extends GameMenuScene {
		
	public SelectGameScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
    protected Map<String, MenuAction> buildOptionMap() {
		Map<String, MenuAction> optionMap = new HashMap<>();
		for(String s : myManager.getLoader().getAvailableGames()){
			optionMap.put(s, () -> selectGame(s));
		}
		return optionMap;
	}
    
    private void selectGame(String gameName){
    	myManager.getLoader().selectGame(gameName);
    	try {
            myManager.getLoader().loadSelectedGame();
            System.out.println("loaded "+gameName);
        }
        catch (GameDataException e) {
        	System.out.println("ERROR LOADING");
        }
    	myManager.sceneFinished();
    }
}
