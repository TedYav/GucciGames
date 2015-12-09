package voogasalad_GucciGames.gameData;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
    
//    public static void main(String[] args){
//        GameListManager m = new GameListManager();
//        m.addGame("testname", "testfile");
//    }
    
    public GameListManager(){
    	loadXML();
    	parseXML();
    }
    
    public void addGame(String gameName, String gameFile){
    	//System.err.println("ERROR: add games manually for now plz :) -> add this to XML FILE\n" + gameName + "\n" + gameFile);
        System.out.println("addgame started");
    	for (String s: listGames()) {
    	    if (s.equals(gameName)) {
    	        System.out.println(gameName+" already added");
    	        return;
    	    }
    	}
    	System.out.println("passed");
    	Element root = myDoc.getDocumentElement();
    	Element game = myDoc.createElement("game");
    	Element name = myDoc.createElement("name");
    	Element file = myDoc.createElement("file");
    	name.appendChild(myDoc.createTextNode(gameName));
    	file.appendChild(myDoc.createTextNode(gameFile));
    	game.appendChild(name);
    	game.appendChild(file);
    	root.appendChild(game);
    	 DOMSource source = new DOMSource(myDoc);
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(myConfig.getString("GameStorageLocation") + myConfig.getString("GameListFile"));
            transformer.transform(source, result);
        }
        catch (Exception e) {
            // TODO -generated catch block
            e.printStackTrace();
        }
    }
    
    public List<String> listGames(){
    	List<String> games = new ArrayList<>();
    	NodeList myGames = myDoc.getElementsByTagName("game");
    	for(int i=0; i<myGames.getLength(); i++){
    		Element curr = (Element)myGames.item(i);
    		games.add(curr.getElementsByTagName("name").item(0).getTextContent());
    	}
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
