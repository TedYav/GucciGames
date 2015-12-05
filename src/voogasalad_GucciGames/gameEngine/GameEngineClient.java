package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thoughtworks.xstream.XStream;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameplayer.controller.GameParametersInterface;

/**
 * This is the wrapper for the client. May be merged with GameEngineServer later
 * on.
 * 
 * @author Efe Aras
 *
 */
public class GameEngineClient implements GameEngineToGamePlayerInterface, Runnable{

	private int myPlayerID;
	private MainGameEngine myEngine;
	private PrintWriter myWriterToServer;
    private String name;

	private static int PORT = 6550; //hard code for now
	private static String SERVER_ADDRESS = ""; //harcode for now

	public GameEngineClient(MainGameEngine engine) {
		myEngine = engine;
	}

	public void updateGameEngine(String engineXML) {
		XStream xstream = new XStream();
		myEngine = (MainGameEngine) xstream.fromXML(engineXML);
	}
	
	//add a listener to handle exceptions and report to front end. alternatively, make this 
	public void run(){
		
        try {
			Socket socket = new Socket(SERVER_ADDRESS, PORT);
			BufferedReader in = new BufferedReader(new InputStreamReader(
		            socket.getInputStream()));
		       myWriterToServer = new PrintWriter(socket.getOutputStream(), true);

		        // Process all messages from server, according to the protocol.
		        while (true) {
		            String input = in.readLine();
		        
		            if(input.equals("GAMEDATA")){
	                    input = in.readLine();
	                    int lengthXML = Integer.parseInt(input);
	                    
	                    StringBuilder myBuilder = new StringBuilder();
	                    
	                    for(int i = 0; i < lengthXML; i++){
	                    	myBuilder.append(in.read());
	                    }
	                    this.updateGameEngine(myBuilder.toString());
	                    
		            }
		            
		           
		        
		        
		        }
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		updateServerGameEngine();
		return myEngine.endTurn();
	}

	private void updateServerGameEngine() {
		// TODO Auto-generated method stub
		XStream xstream = new XStream();

		myWriterToServer.println("GAMEDATA");
		String s = xstream.toXML(myEngine);
		myWriterToServer.println(s.length());
		myWriterToServer.println(s);
		
		
	}

	@Override
	public int getTurnPlayerID() {
		return myEngine.getTurnPlayerID();
	}


	@Override
	public GameParametersInterface getGameParameters() {
		return myEngine.getGameParameters();
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
		return 0;
	}

	@Override
	public int getMapHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return myEngine.getName();
	}

	//change to either immutable or just make the methods of this public (and not the full set...)
	
	
}
