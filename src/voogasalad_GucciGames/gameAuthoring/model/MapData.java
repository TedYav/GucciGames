package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class MapData {
	private List<DisplayMapObject> onMap;
	private String myName;
	private int myWidth;
	private int myHeight;
	
	public MapData(String name, int width, int height) {
		myName = name; 
		myWidth = width;
		myHeight = height;
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
	
	public int getWidth() {
		return myWidth;
	}
	
	public int getHeight() {
		return myHeight;
	}

}
