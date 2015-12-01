package voogasalad_GucciGames.gameData;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ResourceBundle;

public class GameListManager {

    private final ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GameData");

    File myXML;
    
    public GameListManager(){
    	try{
    		myXML = new File(myConfig.getString("GameStorageLocation") + myConfig.getString("GameListFile"));
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void addGame(String gameName, String gameFile){
    	
    }
	
}
