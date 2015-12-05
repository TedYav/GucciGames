package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class MapData {
	private List<DisplayMapObject> onMap;
	
	public void addToMap(DisplayMapObject obj) {
		onMap.add(obj);
	}
	
	public void clearMap() {
		onMap.clear();
	}
	public List<DisplayMapObject> getMapObjects(){
		return onMap;
	}

}
