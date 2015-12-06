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

public class GameEngineServer extends GameEnginePlayer implements Runnable {

	private int myPlayerID;
	private Set<PrintWriter> writers;
    private Set<String> names;
    

	private static int PORT = 6550; //hard code for now
	
	public GameEngineServer(GameEngine gameEngine) {
		super(gameEngine);
		setWriters(new HashSet<PrintWriter>());
		names = new HashSet<String>();
	}

	private void setWriters(HashSet<PrintWriter> hashSet) {
		writers = hashSet;
	}


	public Collection<PrintWriter> getWriters() {
		return writers;
	}

	
	public void updateClientGameEngine() {
		System.out.println("updateclientSize"+writers.size());
		System.out.println(this);
		getWriters().stream().forEach(e -> {
			System.out.println("is this called?");
			XStream xstream = new XStream(new DomDriver());
			String s = xstream.toXML(getMyEngine());
			System.out.println("updating all the clients");
			System.out.println(s.length());
        	e.print("GAMEDATA\n" + s.length() + "\n" + s + "\n");

		/*	e.println("GAMEDATA");
			e.println(s.length());
			e.println(s); */
		});
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
			e.printStackTrace();
		}
	     
        		   
	}

	@Override
	public void endTurn() {
		updateClientGameEngine();
	}


}
