package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

public abstract class MapObjectType implements IMapObjectType {
	String myPath;
	
	MapObjectType(String path) {
		myPath = path;
	}
	
	@Override 
	public String getPath(){
		return myPath;
	}
}
