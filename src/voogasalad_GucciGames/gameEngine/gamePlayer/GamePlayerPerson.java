package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class GamePlayerPerson {
	// neither myPlayerID nor myMapObjects is initialized -> runtime errors
	private int myPlayerId;
	private PlayerResources myResources;
	private List<MapObject> myMapObjects;

	private String myStatus = "DRAW";
	private MovablePlayerCharacteristic myMovable;
	
	
	public GamePlayerPerson(int id) {
		myMapObjects = new ArrayList<MapObject>();
		myPlayerId = id;
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
		return myMapObjects.stream().filter(e -> e.hasCharacteristic("UnitCharacteristic")).collect(Collectors.toList());

	}

	public void setStatus(String status) {
		myStatus = status;

	}

	public int getAllowedMovesPerTurn() {
		return myMovable.getMyNumberOfMoves();
	}



	public int getMovesDoneThisTurn() {
		return myMovable.getMoveCount();
	}

	public void resetObjects(){
		for(MapObject object: myMapObjects){
			if(object.hasCharacteristic("AttackCharacteristic")){
				AttackCharacteristic attachChar =(AttackCharacteristic) object.getCharacteristic("AttackCharacteristic");
				attachChar.reset();
			}

		}
	}

	public MovablePlayerCharacteristic getMovable() {
		return myMovable;
	}

	public void setMovable(MovablePlayerCharacteristic myMovable) {
		this.myMovable = myMovable;
	}

	public EndGameConditions getStatusCondition() {
		
		if(myStatus.equals("WIN")){
			return EndGameConditions.WIN;
		}
		if(myStatus.equals("LOSE")){
			return EndGameConditions.LOSE;
		}
		else{
			return EndGameConditions.DRAW;
		}
	}

}
