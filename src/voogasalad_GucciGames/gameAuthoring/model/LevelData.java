package voogasalad_GucciGames.gameAuthoring.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelData {
	
	private Map<Integer, MapData> myLevels;
	private int nextLevel;
	
	public LevelData () {
    	myLevels = new HashMap<>();
    	nextLevel = 0;
	}

	public void clearLevelMap(int level) {
		myLevels.get(level).clearMap();
	}

	public List<DisplayMapObject> getLevelMapObjects(int level) {
		return myLevels.get(level).getMapObjects();
	}

	public void add(int levelID, DisplayMapObject mapObject) {
		myLevels.get(levelID).addToMap(mapObject);
	}

	public int addLevel(String name) {
		int newLevelID = nextLevel;
		myLevels.put(newLevelID, new MapData(name));
		nextLevel += 1;
		return newLevelID;
		
	}

	public void deleteObject(int levelID, DisplayMapObject mapObj) {
		myLevels.get(levelID).deleteObject(mapObj);
		
	}
	
	public Map<Integer, MapData> getMap() {
		return myLevels;
	}
	

}
