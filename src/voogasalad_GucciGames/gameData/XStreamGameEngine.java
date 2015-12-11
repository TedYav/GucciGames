package voogasalad_GucciGames.gameData;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GamePlayerSave;
import voogasalad_GucciGames.gameData.wrapper.GroovyLoaderData;
import voogasalad_GucciGames.gameEngine.groovyEngine.AGroovyCustomObject;
import voogasalad_GucciGames.gameEngine.groovyEngine.GameGroovyEngine;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyCustomCharacteristic;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyCustomEvent;
import voogasalad_GucciGames.gameEngine.groovyEngine.GroovyLoader;

public class XStreamGameEngine {

    XStream serializer = new XStream(new DomDriver());
    String currentTurn = "Current Turn: ";
    private static FileLoader myLoader = new FileLoader();
    private GameListManager myManager = new GameListManager();
    private GroovyLoaderData loader;
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
    
    public void saveGameLoader(GroovyLoaderData loader, GameInfo game) {
        try {
        String gameXML = serializer.toXML(loader);
        myLoader.save(new File(gameNameToLoaderName(game.getGameName())), gameXML);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
    }
    
    public String gameNameToFileName(String name){
    	return sanitizeGameName(name).append(myConfig.getString("GameExtension")).toString();
    }
    public String gameNameToLoaderName(String name){
        return sanitizeGameName(name).append(myConfig.getString("LoaderExtension")).toString();
    }
    
	public String gameNameToPathName(String name) {
		return sanitizeGameName(name).append("/").toString();
	}

	private StringBuilder sanitizeGameName(String name) {
		StringBuilder sanitizedName = new StringBuilder();
    	sanitizedName.append(myConfig.getString("GameStorageLocation")).append(name.replaceAll("[^a-zA-Z0-9\\._]+", "_"));
		return sanitizedName;
	}


    public GameInfo loadGameByName(String name){
    	return loadGameInfo(new File(gameNameToFileName(name)));
    }
    
    public GameInfo loadGameInfo(String path) {
        return loadGameInfo(new File(path));
    }

    private GameInfo loadGameInfo(File file) {
        GameInfo game=null;
        try {
            List<String> path = Arrays.asList(file.getPath().split(File.separator));
            String dname=path.get(path.size()-1);
            List<String> temp = Arrays.asList(dname.split(Pattern.quote(System.getProperty("file.separator"))));
            String namedd = temp.get(temp.size()-1);
            String name=namedd.substring(0, namedd.length()-3);
            String loaderXML = myLoader.read(new File(gameNameToLoaderName(name)));
            loader = (GroovyLoaderData) serializer.fromXML(loaderXML);
            //loader stuff
            GameGroovyEngine engine = new GameGroovyEngine();
            List<AGroovyCustomObject> groovyCustoms = new ArrayList<AGroovyCustomObject>();
            AGroovyCustomObject custom;
            Map<String,GCharParam> cMap = loader.getGroovyMapObjectCharParams();
            for (String s: cMap.keySet()) {
                custom = new GroovyCustomCharacteristic(s,cMap.get(s).getAllParams());
                groovyCustoms.add(custom);
            }
            Map<String,GActionParams> aMap = loader.getGroovyActionParams();
            for (String s: aMap.keySet()) {
                custom = new GroovyCustomEvent(s,aMap.get(s).getRequest(),aMap.get(s).getAction());
                groovyCustoms.add(custom);
            }
            GroovyLoader gLoader = engine.createLoader(groovyCustoms);
            serializer.setClassLoader(gLoader);
            Thread.currentThread().setContextClassLoader(gLoader);
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
        try {
            String gameXML = myLoader.read(file);
            game = (GameInfo) serializer.fromXML(gameXML);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public GamePlayerSave loadGameState (String saveName, String gameName) {
        File file = new File(gameNameToPathName(gameName) + myConfig.getString("SaveDirectory") + saveName);
        GamePlayerSave game=null;
        try {
            String gameXML = myLoader.read(file);
            game = (GamePlayerSave) serializer.fromXML(gameXML);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }


}
