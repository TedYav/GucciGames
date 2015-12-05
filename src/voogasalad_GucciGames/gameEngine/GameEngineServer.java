package voogasalad_GucciGames.gameEngine;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thoughtworks.xstream.XStream;

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

public class GameEngineServer implements GameEngineToGamePlayerInterface, Runnable {

	private int myPlayerID;
	private GameLevelEngine myEngine;
	private Set<PrintWriter> writers;
    private Set<String> names;

	private static int PORT = 6550; //hard code for now

	public GameEngineServer(GameLevelEngine engine) {
		myEngine = engine;
		setWriters(new HashSet<PrintWriter>());
		names = new HashSet<String>();
	}

	public void updateServerGameEngine(String engineXML) {
		XStream xstream = new XStream();
		myEngine = (GameLevelEngine) xstream.fromXML(engineXML);
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
	@Override
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

	@Override
	public GridCoordinateParameters getPossibleCoordinates(String action, PlayerMapObjectInterface mapObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChangedParameters performAction(String action, PlayerMapObjectInterface mapObject,
			ATargetCoordinate target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMapWidth() {
		// TODO Auto-generated method stub
		return myEngine.getMapWidth();
	}

	@Override
	public int getMapHeight() {
		// TODO Auto-generated method stub
		return myEngine.getMapHeight();
	}


	@Override
	public boolean isGameWon() {
		return myEngine.isGameWon();
	}



	@Override
	public String getName() {
		return myEngine.getName();
	}






}
