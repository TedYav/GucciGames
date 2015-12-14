package voogasalad_GucciGames.gameEngine.networking;

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
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.APlayerChars;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;

/**
 * This is a wrapper class around the game engine. It contains an instance of a
 * game engine and allows updating multiple clients' game engines.
 *
 * @author Efe Aras
 *
 */
@Deprecated
public class GameEngineServer extends GameEngineNetworkThreadActor implements Runnable {

	private int myPlayerID;
	private Set<PrintWriter> writers;
    private Set<String> names;

	private static int PORT = 6555; //hard code for now
	
	public GameEngineServer(GameNetworkEngineInterface gameEngine) {
		super(gameEngine);
		writers = new HashSet<PrintWriter>();
		names = new HashSet<String>();
	}

	public Collection<PrintWriter> getWriters() {
		return writers;
	}

	
	public void updateClientGameEngine() {
		getWriters().stream().forEach(e -> {
			XStream xstream = new XStream(new DomDriver());
		//	String s = xstream.toXML(getMyEngine());
			String s = "";
        	e.print("GAMEDATA\n" + s.length() + "\n" + s + "\n");
        	e.flush();

		});
	}

	//add a listener to handle exceptions and report to front end.
	
	/**
	 * for reference, look here: <a href="http://www.google.com">Google</a>
	 */
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

	

	private void sendClientsIncomingMessage(String string) {
		// TODO Auto-generated method stub
		writers.stream().forEach(e -> {
        	e.print("CHAT\n" + string.length() + "\n" + string + "\n");
        	e.flush();

		});
	}



}
