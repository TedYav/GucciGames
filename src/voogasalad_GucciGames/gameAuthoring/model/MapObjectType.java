package voogasalad_GucciGames.gameAuthoring.model;

public class MapObjectType {
	private double myWidth, myHeight, myX, myY;
	private String myImagePath;
	private String myName;

	public MapObjectType(String name, String imagePath, int i, int j, double d, double e) {
		myName = name;
		myImagePath = imagePath;
		myX = i;
		myY = j;
		myWidth = d;
		myHeight = e;
	}

	public MapObjectType(String name, String imagePath) {
		myImagePath = imagePath;
		myName = name;
	}

	public boolean isTile() {
		// TODO Auto-generated method stub
		// Need to add this once characteristics are added?
		return false;
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

}
