package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

import voogasalad_GucciGames.gameAuthoring.model.factories.GameInfoFactory;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GroovyLoaderData;
import voogasalad_GucciGames.gameData.wrapper.GuiData;

public class ModelSaver {
	private GameInfoFactory myFactory;

	public ModelSaver() {
		myFactory = new GameInfoFactory();
	}

	public void saveGame(GameProperties typeData, LevelData levelData, GuiData guiData, PlayerData playerData,
			String gameName) {
		List<MapObjectType> mapObjectTypeList = typeData.getAllMapObjectTypes();
		GameInfo game = myFactory.create(typeData, levelData, guiData, mapObjectTypeList, gameName);
		XStreamGameEngine saver = new XStreamGameEngine();
		GroovyLoaderData gLoaderData = new GroovyLoaderData(typeData.getGroovyActionParams(),
				typeData.getGroovyMapObjectCharParams());
		saver.saveGameLoader(gLoaderData, game);
		saver.saveGameInfo(game);
		typeData.cleanSave();
	}

}
