package voogasalad_GucciGames.gameAuthoring.model;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.model.holder.ActionHolder;
import voogasalad_GucciGames.gameAuthoring.model.holder.CharacteristicHolder;
import voogasalad_GucciGames.gameEngine.mapObject.AMapObjectCharacteristic;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;

public class MapObjectType {
	private List<AMapObjectCharacteristic> myCharacteristics = new ArrayList<AMapObjectCharacteristic>();
	private List<MapObjectEvent> myEvents;
	private double myWidth, myHeight, myX, myY;
	private String myImagePath;
	private String myName;
	private int myLayer;
	
	public MapObjectType(String name, String imagePath, int layer) {
		myImagePath = imagePath;
		myName = name;
		myLayer = layer;
	}

	public boolean isTile() {
		return myLayer == 0;
	}
	
	public double getWidth() {
		return myWidth;
	}
	
	public double getHeight() {
		return myHeight;
	}
	
	public double getX() {
		return myX;
	}
	
	public double getY() {
		return myY;
	}
	
	public String getImagePath() {
		return myImagePath;
	}
	
	public String getName() {
		return myName;
	}

	public int getLayer() {
		return myLayer;
	}

	public List<AMapObjectCharacteristic> getCharacteristics() {
		return myCharacteristics;
	}

	public List<MapObjectEvent> getEvents() {
		return myEvents;
	}
	
	public void addCharacteristic(AMapObjectCharacteristic characteristic) {
		myCharacteristics.add(characteristic);
	}
	
	public void addAction(MapObjectEvent action) {
		myEvents.add(action);
	}

}
