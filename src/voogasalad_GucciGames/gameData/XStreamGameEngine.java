package voogasalad_GucciGames.gameData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;

public class XStreamGameEngine implements GameDataInterface{

    //@SuppressWarnings("resource")

    XStream serializer = new XStream(new DomDriver());
    String currentTurn = "Current Turn: ";
    private static String defaultEngineLocation = "./src/voogasalad_GucciGames/gameData/engine.xml";
    private static FileLoader myLoader = new FileLoader();


    public void saveEngine(MainGameEngine engine, File file) {
        try {
            String engineXML = serializer.toXML(engine);
            myLoader.save(file, engineXML);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveEngine(MainGameEngine engine, String filePath) {
        try {
            String engineXML = serializer.toXML(engine);
            File file = new File(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(engineXML);
            bufferedWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameEngineToGamePlayerInterface loadEngine() {
        return loadEngine("");
    }

    public GameEngineToGamePlayerInterface loadEngine(String path) {
        String myPath = path;
        if (path.isEmpty()) {
            myPath=defaultEngineLocation;
        }
        System.out.println("Loading engine.");
        MainGameEngine engine=null;
        try {
            File file = new File(myPath);
            //            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            //            StringBuilder engineXMLBuilder = new StringBuilder();
            //            bufferedReader.lines().forEach(line->engineXMLBuilder.append(line));
            String engineXML = myLoader.read(file);

            // loaded XML File should have current turn as 2
            engine = (MainGameEngine) serializer.fromXML(engineXML);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Load complete.");
        return engine;
    }

    @Override
    public void loadGames () {

    }
}
