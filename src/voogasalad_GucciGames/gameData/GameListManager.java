package voogasalad_GucciGames.gameData;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameListManager {

    private final ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GameData");

    File myXML;
    Document myDoc;
    
    public GameListManager(){
    	loadXML();
    	parseXML();
    }
    
    public void addGame(String gameName, String gameFile){
    	System.err.println("ERROR: add games manually for now plz :) -> add this to XML FILE\n" + gameName + "\n" + gameFile);
    }
    
    public List<String> listGames(){
    	List<String> games = new ArrayList<>();
    	NodeList myGames = myDoc.getElementsByTagName("game");
    	for(int i=0; i<myGames.getLength(); i++){
    		Element curr = (Element)myGames.item(i);
    		games.add(curr.getElementsByTagName("name").item(0).getTextContent());
    	}
    	System.out.println(games);
    	return games;
    }

	private void parseXML() {
		try{
    		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	myDoc = dBuilder.parse(myXML);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	private void loadXML() {
		try{
    		myXML = new File(myConfig.getString("GameStorageLocation") + myConfig.getString("GameListFile"));
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
}
