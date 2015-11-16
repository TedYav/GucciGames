package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class GamePlayerPerson {

	private int myPlayerId; // playerID == 0 iff the unit is neutral. (should we
							// make this static?)
	private UnitCollection myUnits;
	// private PlayerGameRule myPlayConditions;
	private PlayerResources myResources;
	private List<MapObject> myMapObjects;

	// why are these here?
/*	private RealHealthCharacteristic myHealth;
	private PlayerHealthRule myHealthRule; */

	// extract these into a separate class
	int unitsMaxMovedLimit;
	int unitsMoved;


	private String myStatus = "DRAW";

	public GamePlayerPerson(UnitCollection units, int playerId) {

		myUnits = units;
		myPlayerId=(playerId);
		this.myMapObjects = new ArrayList<>();

	}

	// should be called by gae when a player is created
/*	public void definePlayerHealth(double healthValue) {
		myHealth = new RealHealthCharacteristic();
		myHealth.defineHealthValue(healthValue);
		myHealthRule = new PlayerHealthRule(myHealth);
	}*/

	public UnitCollection getUnits() {
		return myUnits;
	}

	public List<MapObject> getMapObjects() {
		return this.myMapObjects;
	}

	public void takeTurn() {


		//attack();
		//checkHealth();

	}

	/*private void checkHealth() {
		if (myHealth != null) {
			List<EndGameConditions> list = myHealthRule.executeRule();
			if (list.size() != 0) {
				myStatus = list.get(0).toString();
			}
		}

	}

	public RealHealthCharacteristic myHealth() {
		return myHealth;
	} */

	public String getStatus() {
		return myStatus;
	}
	public void setStatus(String status){
		myStatus=status;
	}

	public int getMyPlayerId() {
		return myPlayerId;
	}





}
