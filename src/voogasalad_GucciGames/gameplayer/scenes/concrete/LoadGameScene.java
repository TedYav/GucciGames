package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import voogasalad_GucciGames.gameData.GameDataException;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;

public class LoadGameScene extends GameMenuScene {

	public LoadGameScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}

    @Override
    protected Map<String, MenuAction> buildOptionMap () {
        Map<String, MenuAction> optionMap = new LinkedHashMap<>();
        for(String s : myManager.getLoader().getAvailableSaves(myManager.getController().getGame().getGameName())){
            optionMap.put(s, () -> selectGameSave(s));
        }
        optionMap.put("Back", () -> myManager.loadScene(myConfig.getString("PrevScene")));
    return optionMap;
    }
    
    private void selectGameSave(String saveName){
        myManager.getLoader().loadGameSave(saveName, myManager.getController().getGame().getGameName());
        myManager.sceneFinished();
    }
}