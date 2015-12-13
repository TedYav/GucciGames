package voogasalad_GucciGames.gameEngine;

public class Main {

	/*
	public static void main(String[] args) {
		// test conditions:
		GamePlayerPerson p0 = new GamePlayerPerson(0);
		GamePlayerPerson p = new GamePlayerPerson(-1);
//		Map<String, List<Integer>> dummyCharacteristicMap = new TreeMap<>();
//		List<Integer> temp = new ArrayList<Integer>();
//		temp.add(1);
//		temp.add(3);
//		dummyCharacteristicMap.put("MovableCharacteristic", temp);

		Map<Integer, GamePlayerPerson> map = new HashMap<Integer, GamePlayerPerson>();

		map.put(-1, p);
		map.put(0, p0);


		AllPlayers allPlayers = new AllPlayers(map);
		GameLevelEngine engine = new GameLevelEngine(allPlayers);
		engine.createTestCondition();

		MovableCharacteristic myMovableCharacteristic = new MovableCharacteristic(1, 3);
		HealthCharacteristic myHealthCharacteristic = new HealthCharacteristic(5);

		MapObject soldier = new MapObject("soldier", "./../");

		MapObject sold1 = new MapObject(soldier, new TargetCoordinateSingle(0, 0),0);

		p0.addMapObject(sold1);

//		soldier.initializeCharacteristicsMap(dummyCharacteristicMap);

		MoveEvent myMoveEvent = new MoveEvent("Move",null,null);
		//WhereToMoveEvent myMoveLocationEvent = new WhereToMoveEvent("WhereToMove");

		sold1.addCharacteristic("MovableCharacteristic", myMovableCharacteristic);
		sold1.addCharacteristic("HealthCharacteristic", myHealthCharacteristic);
		soldier.addEvent("Move", myMoveEvent);
		//soldier.addRequest("WhereToMove", myMoveLocationEvent);


//		engine.getPossibleCoordinates("Move", (PlayerMapObjectInterface) sold1);


		//engine.endTurn();

		// test rules:

	}

*/

}


/*
 * public class Main {
 *
 * public static void main(String[] args){
 *
 *
 * List<GamePlayerPerson> myListOfPlayers = new ArrayList<GamePlayerPerson>();
 *
 *
 * UnitCollection neutralUnits = new UnitCollection(); myListOfPlayers.add(new
 * GamePlayerPerson(neutralUnits, 0)); //neutral player
 *
 * UnitCollection p1Units = new UnitCollection();
 *
 * GameUnitType soldier = new GameUnitType("soldier", null); GameUnitType archer
 * = new GameUnitType("archer" , null);
 *
 * p1Units.addUnit(new GameUnit(1, soldier, 3, 3)); p1Units.addUnit(new
 * GameUnit(1, archer, 1, 1)); myListOfPlayers.add(new GamePlayerPerson(p1Units,
 * 0)); //player 1
 *
 * UnitCollection p2Units = new UnitCollection();
 *
 * p2Units.addUnit(new GameUnit(2, soldier, 2, 2));
 *
 * myListOfPlayers.add(new GamePlayerPerson(p2Units, 0)); //player 2
 *
 *
 * AllPlayers myPlayers = new AllPlayers(myListOfPlayers);
 *
 *
 * GameMap myMap = new GameMap(myPlayers); NoUnitsGlobalGameRule myRule = new
 * NoUnitsGlobalGameRule(myMap); MainGameEngine myEngine = new
 * MainGameEngine(myPlayers, myRule, myMap);
 *
 *
 * while(myRule.gameEnded().size() == 0){
 *
 * myEngine.takeTurn();
 *
 * }
 *
 * } }
 */
// initialize Controller
