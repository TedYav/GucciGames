package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.model.factories.GameInfoFactory;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GroovyLoaderData;
import voogasalad_GucciGames.gameData.wrapper.GuiData;

public class GAEModel implements IGAEModel {
	private GameProperties gameData;
	private GuiData guiData;
	private int defaultOwnerID = -1;
	private LevelData levelData;
	private String gameName;
	private ModelSaver saver;

	public GAEModel() {
		gameData = new GameProperties();
		guiData = new GuiData();
		levelData = new LevelData();
		saver = new ModelSaver();
	}

	@Override
	public void deleteMapObject(int levelID, DisplayMapObject mapObj) {
		levelData.deleteObject(levelID, mapObj);
	}

	@Override
	public List<DisplayMapObject> getMapObjects(int level) {
		return levelData.getLevelMapObjects(level);
	}

	@Override
	public void clearMap(int level) {
		levelData.clearLevelMap(level);
	}

	@Override
	public void createCustomTileType(MapObjectType m) {
		gameData.addMapObjectType(m);
	}

	@Override
	public void createCustomUnitType(MapObjectType m) {
		gameData.addMapObjectType(m);
	}

	@Override
	public void createCustomStructureType(MapObjectType m) {
		gameData.addMapObjectType(m);
	}

	@Override
	public ObservableList<MapObjectType> getImmutableTileTypes() {
		return gameData.getImmutableTileTypes();
	}

	@Override
	public ObservableList<MapObjectType> getImmutableUnitTypes() {
		return gameData.getImmutableUnitTypes();
	}

	@Override
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return gameData.getImmutableStructureTypes();
	}

	public void saveToXML() {
		saver.saveGame(gameData, levelData, guiData, gameName);
	}

	@Override
	public List<String> getComponents(String location) {
		return guiData.getComponents(location);
	}

	@Override
	public void setGuiComponents(String location, List<String> components) {
		guiData.setComponents(location, components);

	}

	@Override
	public int addLevel(String name, int width, int height) {
		return levelData.addLevel(name, width, height);
	}

	@Override
	public DisplayMapObject addMapObject(int levelID, GridPoint gridpoint, MapObjectType mapObjType) {
		return levelData.addMapObject(levelID, gridpoint, defaultOwnerID, mapObjType);
	}

	@Override
	public void setDefaultOwner(int ownerID) {
		defaultOwnerID = ownerID;
	}

	@Override
	public IGameProperties getPropertiesInterface() {
		return gameData;
	}

	@Override
	public void addPlayerCharacteristic(int playerID, ObjParamValue param) {
		gameData.addPlayerCharacteristic(playerID, param);
	}

	@Override
	public void setNumberOfPlayers(int n) {
		gameData.setNumberOfPlayers(n);
	}

	@Override
	public int getNumberOfPlayers() {
		return gameData.getNumberOfPlayers();
	}

	@Override
	public int getDefaultOwner() {
		return defaultOwnerID;
	}

	public void setGameName(String name) {
		gameName = name;
	}

	@Override
	public void deleteMapObjectType(MapObjectType obj) {
		gameData.deleteMapObjectType(obj);
	}

}
