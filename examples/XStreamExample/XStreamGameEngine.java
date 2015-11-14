package XStreamExample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.UnitCollection;
import voogasalad_GucciGames.gameEngine.gameRule.OnlyOnePlayerHasUnitsCondition;

public class XStreamGameEngine {
	
	@SuppressWarnings("resource")
	
	
	public static void main(String[] args0){
		XStream serializer = new XStream(new DomDriver());
		String currentTurn = "Current Turn: ";
		String engineLocation = "./examples/XStreamExample/engine.xml";
		
		System.out.println("Creating and saving engine.");
		try {
			

			List<GamePlayerPerson> myListOfPlayers = new ArrayList<GamePlayerPerson>();	
/*				
			
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
			
			*/
			AllPlayers myPlayers = new AllPlayers(myListOfPlayers);
			
			
			GameMap myMap = new GameMap(myPlayers);
			OnlyOnePlayerHasUnitsCondition myRule = new OnlyOnePlayerHasUnitsCondition(myMap); 
			MainGameEngine engine = new MainGameEngine(myPlayers, myRule, myMap);
			
			
			
			System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 1
			engine.incrementCurrentTurn(); 
			System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 2
			
			String engineXML = serializer.toXML(engine); // saved XML File should have current turn as 2
			
			File file = new File(engineLocation);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(engineXML);
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Save Complete.");
		
		System.out.println("Loading engine.");
		try {
			File file = new File(engineLocation);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			StringBuilder engineXMLBuilder = new StringBuilder();
			bufferedReader.lines().forEach(line->engineXMLBuilder.append(line));
			
			 // loaded XML File should have current turn as 2
			MainGameEngine engine = (MainGameEngine) serializer.fromXML(engineXMLBuilder.toString());
			
			System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 2
			engine.incrementCurrentTurn(); 
			System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 3
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Load complete.");
	}

}
