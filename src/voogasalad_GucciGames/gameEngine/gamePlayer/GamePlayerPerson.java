package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class GamePlayerPerson {
	// neither myPlayerID nor myMapObjects is initialized -> runtime errors
	private int myPlayerId;
	private PlayerResources myResources;
	private List<MapObject> myMapObjects;

	private int turnMoves = -1;// should move this away later
	private int turnCounter = 0;// should move this away later
	private String myStatus = "DRAW";
	private MovablePlayerCharacteristic myMovable;
	
	
	public GamePlayerPerson() {
		myMapObjects = new ArrayList<MapObject>();
	}

	public List<MapObject> getMapObjects() {
		return this.myMapObjects;
	}

	public void addMapObject(MapObject object) {
		myMapObjects.add(object);
	}

	public void endTurn() {

	}

	public int getMyPlayerId() {
		return myPlayerId;
	}

	public List<MapObject> getUnits() {
		// isUnit is not implemented
		return myMapObjects.stream().filter(e -> e.isUnit()).collect(Collectors.toList());

	}

	public void setStatus(String status) {
		myStatus = status;

	}

	public int getAllowedMovesPerTurn() {
		return turnMoves;
	}

	public void setAllowedMovesPerTurn(int turnMoves) {
		this.turnMoves = turnMoves;
	}

	public int getTurnCounter() {
		return turnCounter;
	}

	public void setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
	}
	public void resetObjects(){
		for(MapObject object: myMapObjects){
			if(object.getObjectType().hasCharacteristic("AttackCharacteristic")){
				AttackCharacteristic attachChar =(AttackCharacteristic) object.getObjectType().getCharacteristic("AttackCharacteristic");
				attachChar.reset();
			}

		}
	}

	public MovablePlayerCharacteristic getMyMovable() {
		return myMovable;
	}

	public void setMyMovable(MovablePlayerCharacteristic myMovable) {
		this.myMovable = myMovable;
	}

}
