package voogasalad_GucciGames.gameData;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;

public class XStreamGameEngine {

    //@SuppressWarnings("resource")
	// TODO: refactor constants to resource bundle

    XStream serializer = new XStream(new DomDriver());
    String currentTurn = "Current Turn: ";
    private static FileLoader myLoader = new FileLoader();

    private final ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GameData");
    
    public XStreamGameEngine(){
    }
    
    public void saveGameInfo(GameInfo game, File file) {
        try {
            String gameXML = serializer.toXML(game);
            myLoader.save(file, gameXML);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Saves game in the specified file path. 
     * @param game
     * @param filePath
     */
    public void saveGameInfo(GameInfo game, String filePath) {
        saveGameInfo(game, new File(filePath));
    }
    
    /**
     * Saves the GameInfo automatically based on the specified game's name.
     * @param game
     */
    public void saveGameInfo(GameInfo game){
    	saveGameInfo(game, new File(gameNameToFileName(game.getEngine().getGameName())));
    }
    
    public String gameNameToFileName(String name){
    	StringBuilder sanitizedName = new StringBuilder();
    	sanitizedName.append(myConfig.getString("GameStorageLocation")).append(name.replaceAll("[^a-zA-Z0-9\\._]+", "_")).append(myConfig.getString("GameExtension"));
    	return sanitizedName.toString();
    }

//    public GameEngineToGamePlayerInterface loadEngine() {
//        return loadEngine("");
//    }

    public GameInfo loadGameByName(String name){
    	return loadGameInfo(new File(gameNameToFileName(name)));
    }
    
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

}
