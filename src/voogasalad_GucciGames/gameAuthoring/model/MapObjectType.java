package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

import voogasalad_GucciGames.gameAuthoring.model.holder.ActionHolder;
import voogasalad_GucciGames.gameAuthoring.model.holder.CharacteristicHolder;

public class MapObjectType {
	private List<CharacteristicHolder> myCharacteristics;
	private List<ActionHolder> myActions;
	private double myWidth, myHeight, myX, myY;
	private String myImagePath;
	private String myName;
	private int myLayer;

//	public MapObjectType(String name, String imagePath, int i, int j, double d, double e, int layer) {
//		myName = name;
//		myImagePath = imagePath;
//		myX = i;
//		myY = j;
//		myWidth = d;
//		myHeight = e;
//		myLayer = layer;
//	}
	
	public MapObjectType(String name, String imagePath, int layer) {
		myImagePath = imagePath;
		myName = name;
		myLayer = layer;
	}

	public MapObjectType(String name, String imagePath) {
		myImagePath = imagePath;
		myName = name;
		// TODO get layer from front end dialog box
		myLayer = 0;
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

}
