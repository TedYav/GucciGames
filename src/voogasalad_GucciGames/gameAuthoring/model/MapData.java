package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class MapData {
	private List<MapObject> onMap;

	
	public void addToMap(MapObject obj) {
		onMap.add(obj);
	}
	
	public void clearMap() {
		onMap.clear();
	}
	public List<MapObject> getMapObjects(){
		return onMap;
	}

}
