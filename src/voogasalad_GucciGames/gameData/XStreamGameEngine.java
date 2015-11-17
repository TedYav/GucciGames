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
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;

public class XStreamGameEngine implements GameDataInterface{

    @SuppressWarnings("resource")

    XStream serializer = new XStream(new DomDriver());
    String currentTurn = "Current Turn: ";
    String defaultEngineLocation = "./src/voogasalad_GucciGames/gameData/engine.xml";

    public static void main(String[] args0){
        XStreamGameEngine xStream = new XStreamGameEngine();
        System.out.println("Creating and saving engine.");
        try {
            Map<Integer,GamePlayerPerson> myMapOfPlayers = new TreeMap<Integer,GamePlayerPerson>();	
            myMapOfPlayers.put(-1,new GamePlayerPerson()); //neutral player
            MapObjectType soldier = new MapObjectType("soldier", null);
            MapObjectType archer = new MapObjectType("archer" , null);
            myMapOfPlayers.put(0,new GamePlayerPerson()); //player 1 
            myMapOfPlayers.put(1,new GamePlayerPerson()); //player 2
            AllPlayers myPlayers = new AllPlayers(myMapOfPlayers);

            GameMap myMap = new GameMap(myPlayers);
            OnlyOnePlayerHasUnitsCondition myRule = new OnlyOnePlayerHasUnitsCondition(myMap); 
            
            MainGameEngine engine = new MainGameEngine(myPlayers, myRule, myMap);
            
            String engineXML = xStream.serializer.toXML(engine); // saved XML File should have current turn as 2
            File file = new File(xStream.defaultEngineLocation);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(engineXML);
            bufferedWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Save Complete.");
        xStream.loadEngine();
    }
    public GameEngineToGamePlayerInterface loadEngine() {
        return loadEngine(null);
    }
    public GameEngineToGamePlayerInterface loadEngine(String path) {
        String myPath = path;
        if (path==null) {
            myPath=defaultEngineLocation;
        }
        System.out.println("Loading engine.");
        MainGameEngine engine=null;
        try {
            File file = new File(myPath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder engineXMLBuilder = new StringBuilder();
            bufferedReader.lines().forEach(line->engineXMLBuilder.append(line));

            // loaded XML File should have current turn as 2
            engine = (MainGameEngine) serializer.fromXML(engineXMLBuilder.toString());
            
        } 
        catch (FileNotFoundException e) {
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
