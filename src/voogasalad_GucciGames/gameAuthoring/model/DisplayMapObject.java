package voogasalad_GucciGames.gameAuthoring.model;

import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class DisplayMapObject {
	private MapObjectType myType;
	private int myOwnerID;
	private TargetCoordinateSingle myCoord;
	private int myLayer;
	
	public DisplayMapObject(MapObjectType mapObjType, 
			TargetCoordinateSingle targCoordSingle, int ownerID, int layer) {
		myType = mapObjType;
		myCoord = targCoordSingle;
		myOwnerID = ownerID;
		myLayer = layer;
		
	
	}
	
	public MapObjectType getType(){
		return myType;
	}

	public int getOwnerID() {
		return myOwnerID;
	}
	
	
	

}
