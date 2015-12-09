package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class MapData {
	private List<DisplayMapObject> onMap;
	private String myName;
	
	public MapData(String name) {
		myName = name; 
		onMap = new ArrayList<>();
	}

	public void addToMap(DisplayMapObject obj) {
		onMap.add(obj);
	}
	
	public void clearMap() {
		onMap.clear();
	}
	public List<DisplayMapObject> getMapObjects(){
		return onMap;
	}

	public void deleteObject(DisplayMapObject mapObj) {
		onMap.remove(mapObj);
	}
	
	public String getName() {
		return myName;
	}

}
