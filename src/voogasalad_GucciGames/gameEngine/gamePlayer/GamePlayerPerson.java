package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.gameConditions.EndGameConditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerMovesPerTurn;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class GamePlayerPerson {
	private int myPlayerId;
	private static final String NUM_OF_MOVES = "numOfMoves";
	private List<MapObject> myMapObjects;
	private Map<String, APlayerChars> myCharacteristics = new HashMap<String, APlayerChars>();

	private String myStatus = "DRAW";
	private PlayerMovesPerTurn myMovable;

	public GamePlayerPerson(int id) {
		myMapObjects = new ArrayList<MapObject>();
		myPlayerId = id;
		myMovable = new PlayerMovesPerTurn();
		myCharacteristics.put(NUM_OF_MOVES, myMovable);
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
		return myMapObjects.stream().filter(e -> e.hasCharacteristic("UnitCharacteristic"))
				.collect(Collectors.toList());

	}

	public void setStatus(String status) {
		myStatus = status;

	}

	public int getAllowedMovesPerTurn() {
		return ((PlayerMovesPerTurn) myCharacteristics.get(NUM_OF_MOVES)).getMyNumberOfMoves();
	}

	public int getMovesDoneThisTurn() {
		return ((PlayerMovesPerTurn) myCharacteristics.get(NUM_OF_MOVES)).getMoveCount();
	}

	public void resetObjects() {
		for (MapObject object : myMapObjects) {
			if (object.hasCharacteristic("AttackCharacteristic")) {
				AttackCharacteristic attachChar = (AttackCharacteristic) object
						.getCharacteristic("AttackCharacteristic");
				attachChar.reset();
			}

		}
	}

	public PlayerMovesPerTurn getMovable() {
		return ((PlayerMovesPerTurn) myCharacteristics.get(NUM_OF_MOVES));
	}

	public void setMovable(PlayerMovesPerTurn myMovable) {
		myCharacteristics.put(NUM_OF_MOVES, myMovable);
	}

	public EndGameConditions getStatusCondition() {

		if (myStatus.equals("WIN")) {
			return EndGameConditions.WIN;
		}
		if (myStatus.equals("LOSE")) {
			return EndGameConditions.LOSE;
		} else {
			return EndGameConditions.DRAW;
		}
	}

	public APlayerChars getCharacteristics(String name) {
		return myCharacteristics.get(name);
	}

	public Boolean hasCharacteristic(String name) {
		return myCharacteristics.containsKey(name);
	}

	public void addCharacterstic(String name, APlayerChars charInstance) {
		this.myCharacteristics.put(name, charInstance);
	}
	
	public void clearMapObjects() {
		myMapObjects.clear();
	}

}
