package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class GamePlayerPerson {

	private int myPlayerId; 
	private PlayerResources myResources;
	private List<MapObject> myMapObjects;
	
	private int unitsMoved=0;
	private int unitsMaxMovedLimit;
	private String myStatus = "DRAW";

	public List<MapObject> getMapObjects() {
		return this.myMapObjects;
	}

	public void endTurn() {
		
	}

	public int getMyPlayerId() {
		return myPlayerId;
	}

	public List<MapObject> getUnits() {

		return myMapObjects.stream().filter(e -> e.isUnit()).collect(Collectors.toList());

	}

	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}

	public int getUnitsMoved() {
		// TODO Auto-generated method stub
		return 0;
	}


}
