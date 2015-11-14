package voogasalad_GucciGames;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.UnitCollection;
import voogasalad_GucciGames.gameEngine.gameRule.NoUnitsGlobalGameRule;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnit;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnitType;

public class Main {

	public static void main(String[] args){


	List<GamePlayerPerson> myListOfPlayers = new ArrayList<GamePlayerPerson>();


	UnitCollection neutralUnits = new UnitCollection();
	myListOfPlayers.add(new GamePlayerPerson(neutralUnits, 0)); //neutral player

	UnitCollection p1Units = new UnitCollection();

	GameUnitType soldier = new GameUnitType("soldier", null);
	GameUnitType archer = new GameUnitType("archer" , null);

	p1Units.addUnit(new GameUnit(1, soldier, 3, 3));
	p1Units.addUnit(new GameUnit(1, archer, 1, 1));
	myListOfPlayers.add(new GamePlayerPerson(p1Units, 0)); //player 1

	UnitCollection p2Units = new UnitCollection();

	p2Units.addUnit(new GameUnit(2, soldier, 2, 2));

	myListOfPlayers.add(new GamePlayerPerson(p2Units, 0)); //player 2


	AllPlayers myPlayers = new AllPlayers(myListOfPlayers);


	GameMap myMap = new GameMap(myPlayers);
	NoUnitsGlobalGameRule myRule = new NoUnitsGlobalGameRule(myMap);
	MainGameEngine myEngine = new MainGameEngine(myPlayers, myRule, myMap);


	while(myRule.gameEnded().size() == 0){

		myEngine.takeTurn();

	}

	}

	//initialize Controller
}
