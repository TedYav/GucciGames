package voogasalad_GucciGames.gameAuthoring.model;

import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class DisplayMapObject {
	private MapObjectType myType;
	private int myOwnerID;
	private GridPoint myCoord;
	private int myLayer;
	
	public DisplayMapObject(MapObjectType mapObjType, 
			GridPoint gridPoint, int ownerID, int layer) {
		myType = mapObjType;
		myCoord = gridPoint;
		myOwnerID = ownerID;
		myLayer = layer;
		
	
	}
	
	public MapObjectType getType(){
		return myType;
	}

	public int getOwnerID() {
		return myOwnerID;
	}
	
	public GridPoint getCoordinate() {
		return myCoord;
	}
	
	
	

}
