package voogasalad_GucciGames.gameData;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GamePlayerSave;
import voogasalad_GucciGames.gameEngine.GameEngineToGamePlayerInterface;

public class XStreamGameEngine {

    //@SuppressWarnings("resource")
	// TODO: refactor constants to resource bundle

    XStream serializer = new XStream(new DomDriver());
    String currentTurn = "Current Turn: ";
    private static FileLoader myLoader = new FileLoader();
    private GameListManager myManager = new GameListManager();

    private final ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GameData");
    
    public XStreamGameEngine(){
    }
    
    public void saveGameInfo(GameInfo game, File file) {
        try {
            String gameXML = serializer.toXML(game);
            myLoader.save(file, gameXML);
            myManager.addGame(game.getGameName(), sanitizeGameName(game.getGameName())+ myConfig.getString("GameExtension"));
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
    	saveGameInfo(game, new File(gameNameToFileName(game.getGameName())));
    }
    
    public void saveGameState(GamePlayerSave game, File file) {
        try {
            String gameXML = serializer.toXML(game);
            myLoader.save(file, gameXML);
            //myManager.addGame(game.getGameName(), sanitizeGameName(game.getGameName())+ myConfig.getString("GameExtension"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveGameState(GamePlayerSave game){
        saveGameState(game, new File(gameNameToPathName(game.getInfo().getGameName()) + myConfig.getString("SaveDirectory") + DateFormat.getDateInstance().format(new Date()) +" "+ DateFormat.getTimeInstance().format(new Date()).replace(':', '-') + myConfig.getString("SaveExtension")));
        System.out.println("SAVED");
    }
    
    public String gameNameToFileName(String name){
    	return sanitizeGameName(name).append(myConfig.getString("GameExtension")).toString();
    }
    
	public String gameNameToPathName(String name) {
		return sanitizeGameName(name).append("/").toString();
	}

	private StringBuilder sanitizeGameName(String name) {
		StringBuilder sanitizedName = new StringBuilder();
    	sanitizedName.append(myConfig.getString("GameStorageLocation")).append(name.replaceAll("[^a-zA-Z0-9\\._]+", "_"));
		return sanitizedName;
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

    public GamePlayerSave loadGameState (String saveName, String gameName) {
        // TODO Auto-generated method stub
        File file = new File(gameNameToPathName(gameName) + myConfig.getString("SaveDirectory") + saveName);
        System.out.println("Loading SAVE."+saveName);
        GamePlayerSave game=null;
        try {
            String gameXML = myLoader.read(file);
            game = (GamePlayerSave) serializer.fromXML(gameXML);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Load complete.");
        return game;
    }


}
