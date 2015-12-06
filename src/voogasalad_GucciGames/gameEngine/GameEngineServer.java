package voogasalad_GucciGames.gameEngine;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;

/**
 * This is a wrapper class around the game engine. It contains an instance of a
 * game engine and allows updating multiple clients' game engines.
 *
 * @author Efe Aras
 *
 */

public class GameEngineServer implements Runnable {

	private int myPlayerID;
	private GameLevelEngine myEngine;
	private Set<PrintWriter> writers;
    private Set<String> names;
    
    private GameEngine mySuperEngine;

	private static int PORT = 6011; //hard code for now
	
	public GameEngineServer(GameEngine gameEngine) {
		// TODO Auto-generated constructor stub
		mySuperEngine = gameEngine;
		setWriters(new HashSet<PrintWriter>());
		names = new HashSet<String>();
	}

	private void setWriters(HashSet<PrintWriter> hashSet) {
		writers = hashSet;
	}

	public void setLevelEngine(GameLevelEngine currentLevel) {
		myEngine = currentLevel;
	}

	public void updateServerGameEngine(String engineXML) {
		
		XStream xstream = new XStream(new DomDriver());
		myEngine = (GameLevelEngine) xstream.fromXML(engineXML);
		this.mySuperEngine.addLevel(myEngine.getLevelName(), myEngine);
		
	}

	public void updateClientGameEngine() {
		System.out.println("updateclientge"+writers.size());
		System.out.println(this);
		getWriters().stream().forEach(e -> {
			XStream xstream = new XStream(new DomDriver());
			System.out.println("updating client");
			e.println("GAMEDATA");
			String s = xstream.toXML(myEngine);
			e.println(s.length());
			e.println(s);
		});
	}

	public Collection<PrintWriter> getWriters() {
		return writers;
	}

	//add a listener to handle exceptions and report to front end.
	@Override
	public void run(){
		ServerSocket listener = null;
        try {
			listener = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        try {
        	while(true){
        	
			new GameEngineConnectionHandler(listener.accept(), this).start();
        	
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
        		   
	}


}
