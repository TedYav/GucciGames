package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;
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
public class GameEngineClient extends GameEnginePlayer implements Runnable{

	private int myPlayerID;
	private PrintWriter myWriterToServer;
    private String name;

	private static int PORT = 6550; //hard code for now
	private static String SERVER_ADDRESS = "10.190.209.220"; //harcode for now
	
	public GameEngineClient(GameEngine gameEngine, String ipAddr) {
		super(gameEngine);
		//ignore the ip address for now;
	}

	//add a listener to handle exceptions and report to front end. alternatively, make this
	@Override
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
	
	private void updateServerGameEngine() {
		// TODO Auto-generated method stub
		XStream xstream = new XStream();

		myWriterToServer.println("GAMEDATA");
		String s = xstream.toXML(getMyEngine());
		myWriterToServer.println(s.length());
		myWriterToServer.println(s);


	}

	@Override
	public void endTurn() {
		updateServerGameEngine();
	}
}