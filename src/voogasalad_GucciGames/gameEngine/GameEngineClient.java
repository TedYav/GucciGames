package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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

	private static int PORT = 6555; //hard code for now
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
                    System.out.println("waiting for server input");
		            String input = in.readLine();
		            
                    if (input == null) {
                        return;
                    }
                    System.out.println("client has:" + input.substring(0, 7));
                    if(input.startsWith("GAMEDATA")){
	                   // input = in.readLine();
                        System.out.println("OMG SOME DATA ON CLIENT");
                    	
	                    input = in.readLine();
                    	
                    	
	                    int lengthXML = Integer.parseInt(input);
	                    System.out.println("according to input the length is" + lengthXML);
	                    StringBuilder myBuilder = new StringBuilder();

	                    for(int i = 0; i < lengthXML; i++){
	                    	myBuilder.append((char) in.read());
	                    }
	                    
	                    System.out.println("actual input is" + myBuilder.toString().length());

	                    
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

		XStream xstream = new XStream(new DomDriver());
		String s = xstream.toXML(getMyEngine());
		System.out.println("updating all the clients");
		System.out.println(s.length());
    	myWriterToServer.print("GAMEDATA\n" + s.length() + "\n" + s + "\n");


	}

	@Override
	public void endTurn() {
		updateServerGameEngine();
	}
}