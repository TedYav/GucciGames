package voogasalad_GucciGames.gameData;

import java.io.File;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameEngine.MainGameEngine;
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
        	saveEngine(engine, new File(filePath));
    }

    public GameEngineToGamePlayerInterface loadEngine() {
        return loadEngine("");
    }

    public GameEngineToGamePlayerInterface loadEngine(String path) {
        if (path.isEmpty()) {
            path=defaultEngineLocation;
        }
        return loadEngine(new File(path));
    }
    
    public GameEngineToGamePlayerInterface loadEngine(File file) {
        if (file==null || !file.canRead()) {
        	file = new File(defaultEngineLocation);
        }
        System.out.println("Loading engine.");
        MainGameEngine engine=null;
        try {
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
