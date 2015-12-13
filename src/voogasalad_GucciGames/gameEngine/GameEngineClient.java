package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

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
	private static String SERVER_ADDRESS = "10.191.241.104"; //harcode for now
	
	public GameEngineClient(GameEngine gameEngine, String ipAddr) {
		super(gameEngine);
	}

	@Override
	public void run(){

        try {
			Socket socket = new Socket(SERVER_ADDRESS, PORT);
			BufferedReader in = new BufferedReader(new InputStreamReader(
		            socket.getInputStream()));
		       myWriterToServer = new PrintWriter(socket.getOutputStream(), true);

		        while (true) {
                    System.out.println("waiting for server input");
		            String input = in.readLine();
		            
                    if (input == null) {
                        return;
                    }
                    System.out.println("client has:" + input);
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
                    
                    if(input.startsWith("CHAT")){
                    	  input = in.readLine();
                      	
                      	
  	                    int lengthMessage = Integer.parseInt(input);
	                    StringBuilder myBuilder = new StringBuilder();

  	                  for(int i = 0; i < lengthMessage; i++){
	                    	myBuilder.append((char) in.read());
	                    }
	                    
	                    this.updateChat(myBuilder.toString());
  	                    
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
		System.out.println("updating the server");
		System.out.println(s.length());
    	myWriterToServer.print("GAMEDATA\n" + s.length() + "\n" + s + "\n");
    	myWriterToServer.flush();


	}

	@Override
	public void endTurn() {
		updateServerGameEngine();
	}

	@Override
	public void sendMessage(String string) {
		// TODO Auto-generated method stub
		myWriterToServer.print("CHAT\n" + string.length() + "\n" + string + "\n");
    	myWriterToServer.flush();

		
	}
}
