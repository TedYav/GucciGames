package voogasalad_GucciGames.gameAuthoring.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;

public class LevelData {
	private Map<Integer, MapData> myLevels = new HashMap<>();
	private int nextLevel = 0;

	public void clearLevelMap(int level) {
		myLevels.get(level).clearMap();
	}

	public List<DisplayMapObject> getLevelMapObjects(int level) {
		return myLevels.get(level).getMapObjects();
	}

	public void add(int levelID, DisplayMapObject mapObject) {
		myLevels.get(levelID).addToMap(mapObject);
	}

	public int addLevel(String name, int width, int height) {
		int newLevelID = nextLevel;
		myLevels.put(newLevelID, new MapData(name, width, height));
		nextLevel += 1;
		return newLevelID;
	}

	public void deleteObject(int levelID, DisplayMapObject mapObj) {
		myLevels.get(levelID).deleteObject(mapObj);
	}

	public Map<Integer, MapData> getMap() {
		return myLevels;
	}

	public DisplayMapObject addMapObject(int levelID, GridPoint gridpoint, int owner, MapObjectType mapObjType) {
		DisplayMapObject mapObj = new DisplayMapObject(mapObjType, gridpoint, owner, mapObjType.getLayer());
		add(levelID, mapObj);
		return mapObj;
	}

}
