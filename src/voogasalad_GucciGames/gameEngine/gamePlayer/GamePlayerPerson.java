package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class GamePlayerPerson {

	private int myPlayerId; // playerID == 0 iff the unit is neutral. (should we
							// make this static?)
	private UnitCollection myUnits;
	private PlayerResources myResources;
	private List<MapObject> myMapObjects;
	private int unitsMoved=0;
	int unitsMaxMovedLimit;
	private String myStatus = "DRAW";

	public GamePlayerPerson(UnitCollection units, int playerId) {

		myUnits = units;
		myPlayerId = (playerId);
		this.myMapObjects = new ArrayList<>();

	}

	// should be called by gae when a player is created
	/*
	 * public void definePlayerHealth(double healthValue) { myHealth = new
	 * RealHealthCharacteristic(); myHealth.defineHealthValue(healthValue);
	 * myHealthRule = new PlayerHealthRule(myHealth); }
	 */

	public UnitCollection getUnits() {
		return myUnits;
	}

	public List<MapObject> getMapObjects() {
		return this.myMapObjects;
	}

	public void takeTurn() {


	}

	public String getStatus() {
		return myStatus;
	}

	public void setStatus(String status) {
		myStatus = status;
	}

	public int getMyPlayerId() {
		return myPlayerId;
	}

	public int getUnitsMoved() {
		return unitsMoved;
	}

	public void setUnitsMoved(int unitsMoved) {
		this.unitsMoved = unitsMoved;
	}

}
