package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thoughtworks.xstream.XStream;

import voogasalad_GucciGames.gameEngine.CommunicationParams.ActionToGamePlayerParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;

/**
 * This is a wrapper class around the game engine. It contains an instance of a
 * game engine and allows updating multiple clients' game engines.
 * 
 * @author Efe Aras
 *
 */

public class GameEngineServer implements GameEngineToGamePlayerInterface, Runnable {

	private int myPlayerID;
	private MainGameEngine myEngine;
	private Set<PrintWriter> writers;
    private Set<String> names;

	private static int PORT = 6550; //hard code for now

	public GameEngineServer(MainGameEngine engine) {
		myEngine = engine;
		setWriters(new HashSet<PrintWriter>());
		names = new HashSet<String>();
	}

	public void updateServerGameEngine(String engineXML) {
		XStream xstream = new XStream();
		myEngine = (MainGameEngine) xstream.fromXML(engineXML);
	}

	public void updateClientGameEngine() {
		getWriters().stream().forEach(e -> {
			XStream xstream = new XStream();
			e.println("GAMEDATA");
			String s = xstream.toXML(myEngine);
			e.println(s.length());
			e.println(s);
		});
	}
	
	//add a listener to handle exceptions and report to front end.
	public void run(){
		ServerSocket listener = null;
        try {
			listener = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
	            while (true) {
	                new GameEngineConnectionHandler(listener.accept(), this).start();
	            	
	            }
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	        }
	}

	@Override
	public String getGameName() {
		return myEngine.getGameName();
	}

	@Override
	public List<PlayerMapObjectInterface> getInitialState() {
		return myEngine.getInitialState();
	}

	@Override
	public GameParametersInterface endTurn() {
		updateClientGameEngine();
		return myEngine.endTurn();
	}

	@Override
	public int getTurnPlayerID() {
		return myEngine.getTurnPlayerID();
	}

	@Override
	public GridCoordinateParameters getPossibleCoordinates(String action, PlayerMapObjectInterface mapObject) {
		// deprecated method
		return null;
	}

	@Override
	public ActionToGamePlayerParameters performAction(String action, PlayerMapObjectInterface mapObject,
			ATargetCoordinate target) {
		// deprecated method
		return null;
	}

	@Override
	public double getMapWidth() {
		return myEngine.getMapWidth();
	}

	@Override
	public double getMapHeight() {
		return myEngine.getMapHeight();
	}

	@Override
	public GameParametersInterface getGameParameters() {
		return myEngine.getGameParameters();
	}

	//change to either immutable or just make the methods of this public (and not the full set...)
	public Set<String> getNames() {
		return names;
	}

	public Set<PrintWriter> getWriters() {
		return writers;
	}

	public void setWriters(Set<PrintWriter> writers) {
		this.writers = writers;
	}

	
	
	//http://cs.lmu.edu/~ray/notes/javanetexamples/#chat
		
	
	

}
