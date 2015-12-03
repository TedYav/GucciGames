package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameEngineConnectionHandler extends Thread {

	        private String name;
	        private Socket socket;
	        private BufferedReader in;
	        private PrintWriter out;
	        private GameEngineServer myServer; //get the instance of a server
	        
	        
	        /**
	         * Constructs a handler thread, squirreling away the socket.
	         * All the interesting work is done in the run method. 
	         */
	        public GameEngineConnectionHandler(Socket socket, GameEngineServer server) {
	            this.socket = socket;
	            this.myServer = server;
	        }

	        /**
	         * Services this thread's client by repeatedly requesting a
	         * screen name until a unique one has been submitted, then
	         * acknowledges the name and registers the output stream for
	         * the client in a global set, then repeatedly gets inputs and
	         * broadcasts them.
	         */
	        public void run() {
	            try {

	                // Create character streams for the socket.
	                in = new BufferedReader(new InputStreamReader(
	                    socket.getInputStream()));
	                out = new PrintWriter(socket.getOutputStream(), true);

	                // Request a name from this client.  Keep requesting until
	                // a name is submitted that is not already used.  Note that
	                // checking for the existence of a name and adding the name
	                // must be done while locking the set of names.
	                while (true) {
	                    out.println("SUBMITNAME");
	                    name = in.readLine();
	                    if (name == null) {
	                        return;
	                    }
	                    synchronized (myServer.getNames()) {
	                        if (!myServer.getNames().contains(name)) {
	                            myServer.getNames().add(name);
	                            break;
	                        }
	                    }
	                }

	                // Now that a successful name has been chosen, add the
	                // socket's print writer to the set of all writers so
	                // this client can receive broadcast messages.
	                out.println("NAMEACCEPTED");
	                myServer.getWriters().add(out);

	                // Accept messages from this client and broadcast them.
	                // Ignore other clients that cannot be broadcasted to.
	                while (true) {
	                    String input = in.readLine();
	                    if (input == null) {
	                        return;
	                    }
	                    if(input.equals("GAMEDATA")){
	                    input = in.readLine();
	                    int lengthXML = Integer.parseInt(input);
	                    
	                    StringBuilder myBuilder = new StringBuilder();
	                    
	                    for(int i = 0; i < lengthXML; i++){
	                    	myBuilder.append(in.read());
	                    }
	                    myServer.updateServerGameEngine(myBuilder.toString());
	                    
	                    for (PrintWriter writer : myServer.getWriters()) {
	                        writer.println("GAMEDATA");
	                        writer.println(lengthXML);
	                        writer.println(myBuilder.toString());
	                    }
	                    
	                    
	                    
	                    }
	                    
	                    //if input == "GAMEDATA"
	                    //input = in.readLine();
	                    //read that many characters and append to a StringBuilder, then make it a string.
	                    //try to update the engine that way
	                    //print out the xml on console as a debug
	                    //if doesn't work, check the end conditions (missing characters and whatnot)
	                    
	           
	                }
	            } catch (IOException e) {
	                System.out.println(e);
	            } finally {
	                // This client is going down!  Remove its name and its print
	                // writer from the sets, and close its socket.
	                if (name != null) {
	                    myServer.getNames().remove(name);
	                }
	                if (out != null) {
	                    myServer.getWriters().remove(out);
	                }
	                try {
	                    socket.close();
	                } catch (IOException e) {
	                }
	            }
	        }
	    
	
	
}
