package voogasalad_GucciGames.gameData;

import java.io.File;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameDataInterface;

public class XStreamGameEngine implements GameDataInterface{

    //@SuppressWarnings("resource")

    XStream serializer = new XStream(new DomDriver());
    //private static String defaultEngineLocation = "./src/voogasalad_GucciGames/gameData/engine.xml";
    private static FileLoader myLoader = new FileLoader();

    public void saveGameInfo(GameInfo game, File file) {
        try {
            String gameXML = serializer.toXML(game);
            myLoader.save(file, gameXML);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveGameInfo(GameInfo engine, String filePath) {
        saveGameInfo(engine, new File(filePath));
    }

//    public GameEngineToGamePlayerInterface loadEngine() {
//        return loadEngine("");
//    }

    public GameInfo loadGameInfo(String path) {
//        if (path.isEmpty()) {
//            path=defaultEngineLocation;
//        }
        return loadGameInfo(new File(path));
    }

    public GameInfo loadGameInfo(File file) {
//        if (file==null || !file.canRead()) {
//            file = new File(defaultEngineLocation);
//        }
        System.out.println("Loading engine.");
        GameInfo game=null;
        try {
            String gameXML = myLoader.read(file);
            game = (GameInfo) serializer.fromXML(gameXML);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Load complete.");
        return game;
    }

    @Override
    public void loadGames () {

    }
}
