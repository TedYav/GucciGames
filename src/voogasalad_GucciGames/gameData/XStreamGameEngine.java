package voogasalad_GucciGames.gameData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.game.OnlyOnePlayerHasUnitsCondition;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;

public class XStreamGameEngine implements GameDataInterface{

    @SuppressWarnings("resource")

    XStream serializer = new XStream(new DomDriver());
    String currentTurn = "Current Turn: ";
    String engineLocation = "./src/voogasalad_GucciGames/gameData/engine.xml";

    public static void main(String[] args0){
        XStreamGameEngine xStream = new XStreamGameEngine();
        System.out.println("Creating and saving engine.");
        try {
            Map<Integer,GamePlayerPerson> myMapOfPlayers = new TreeMap<Integer,GamePlayerPerson>();	
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
            AllPlayers myPlayers = new AllPlayers(myMapOfPlayers);


            GameMap myMap = new GameMap(myPlayers);
            OnlyOnePlayerHasUnitsCondition myRule = new OnlyOnePlayerHasUnitsCondition(myMap); 
            MainGameEngine engine = new MainGameEngine(myPlayers, myRule, myMap);



            //System.out.println(xStream.currentTurn + engine.getCurrentTurn()); // current turn should be 1
            //engine.endTurn(); 
            //System.out.println(xStream.currentTurn + engine.getCurrentTurn()); // current turn should be 2

            String engineXML = xStream.serializer.toXML(engine); // saved XML File should have current turn as 2

            File file = new File(xStream.engineLocation);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(engineXML);
            bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Save Complete.");

        xStream.loadEngine();
    }
    public GameEngineToGamePlayerInterface loadEngine() {
        System.out.println("Loading engine.");
        MainGameEngine engine=null;
        try {
            File file = new File(engineLocation);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder engineXMLBuilder = new StringBuilder();
            bufferedReader.lines().forEach(line->engineXMLBuilder.append(line));

            // loaded XML File should have current turn as 2
            engine = (MainGameEngine) serializer.fromXML(engineXMLBuilder.toString());

            //System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 2
            //engine.endTurn(); 
            //System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 3
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Load complete.");
        return engine;
    }
    @Override
    public void loadGames () {
        // TODO Auto-generated method stub
        
    }
}
