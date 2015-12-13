package voogasalad_GucciGames.gameplayer.scenes.concrete;

import java.util.LinkedHashMap;
import java.util.Map;

import voogasalad_GucciGames.gameplayer.scenes.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;

public class SelectGameTypeScene extends GameMenuScene {

	public SelectGameTypeScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}

	@Override
	protected Map<String, MenuAction> buildOptionMap() {
		Map<String, MenuAction> optionMap = new LinkedHashMap<>();
		optionMap.put("Local Game", () -> myManager.loadScene("SelectLevelScene"));
		optionMap.put("Network Game", () -> myManager.loadScene("NetworkConfigScene"));
		optionMap.put("Back", () -> myManager.loadScene(myConfig.getString("PrevScene")));
		return optionMap;
	}
}
