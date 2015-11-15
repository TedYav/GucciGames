package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.RealHealthCharacteristic;
import voogasalad_GucciGames.gameEngine.gameRule.EndGameConditions;
import voogasalad_GucciGames.gameEngine.gameRule.PlayerHealthRule;
import voogasalad_GucciGames.gameEngine.gameRule.Goal.Goal;
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

	Goal myGoal;

	private String myStatus = "goalNotAchieved";

	public GamePlayerPerson(UnitCollection units, int playerId) {

		myUnits = units;
		myPlayerId = playerId;
		myGoal = new Goal();
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

	/*private void attack() {
		System.out.println(myUnits);

		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Enter the unit to act: (a number from 0 up to " + (myUnits.size() - 1));
		int n = reader.nextInt(); // Scans the next token of the input as an
									// int.

		MapObject unit = myUnits.getUnit(n);

		System.out.println("What would you like to do? 0: move, 1: attack");
		n = reader.nextInt(); // Scans the next token of the input as an int.

		if (n == 0) {

			System.out.println("Where would you like to move? Pick the correct point on the spot");

			// unit.performAction("move");
		}

		else {
			System.out.println("Where would you like to attack? Pick the correct point on the spot");

			// unit.performAction("attack");
		}
	} */

}
