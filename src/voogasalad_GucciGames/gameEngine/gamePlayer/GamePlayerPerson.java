package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.List;
import java.util.Scanner;

import voogasalad_GucciGames.gameEngine.gameRule.PlayerGameRule;
import voogasalad_GucciGames.gameEngine.gameRule.Goal.Goal;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnit;

public class GamePlayerPerson {

	private int myPlayerId; // playerID == 0 iff the unit is neutral. (should we
							// make this static?)
	private UnitCollection myUnits;
	private PlayerGameRule myPlayConditions;
	private PlayerResources myResources;

	private Goal myGoal;
	private String myGoalStatus="goalNotAchieved";

	
	public GamePlayerPerson(UnitCollection units, int playerId){
		

		myUnits = units;
		myPlayerId = playerId;
		myGoal = new Goal();

	}

	public UnitCollection getUnits() {

		return myUnits;
	}

	public void takeTurn() {

		System.out.println(myUnits);

		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Enter the unit to act: (a number from 0 up to " + (myUnits.size() - 1));
		int n = reader.nextInt(); // Scans the next token of the input as an
									// int.

		GameUnit unit = myUnits.getUnit(n);

		System.out.println("What would you like to do? 0: move, 1: attack");
		n = reader.nextInt(); // Scans the next token of the input as an int.

		if (n == 0) {

			System.out.println("Where would you like to move? Pick the correct point on the spot");

			unit.performAction("move");
		}

		else {
			System.out.println("Where would you like to attack? Pick the correct point on the spot");

			unit.performAction("attack");
		}

		myGoal.checkSatisfied();
		myGoalStatus= myGoal.getMyStatus();

	}
	public void CreatGoal(List<String> names, List<Double> values){
		myGoal.addRequirement(names, values);
	}

	public String getMyGoalStatus() {
		return myGoalStatus;
	}

}
