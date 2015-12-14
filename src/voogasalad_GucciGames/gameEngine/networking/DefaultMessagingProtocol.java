package voogasalad_GucciGames.gameEngine.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class DefaultMessagingProtocol extends GameMessagingProtocol{

	public String receiveMessage(Reader in2) throws IOException{
		BufferedReader in = new BufferedReader(in2);
        String input = in.readLine();
        int lengthXML = Integer.parseInt(input);
        StringBuilder myBuilder = new StringBuilder();

        for(int i = 0; i < lengthXML; i++){
        	myBuilder.append((char) in.read());
        }

		return myBuilder.toString();
		
	}
	
	public String sendMessage(String tag, String message, PrintWriter writer){
		
		String myMessage = tag + "\n" + message.length() + "\n" + message + "\n";
		
    	writer.print(tag + "\n" + message.length() + "\n" + message + "\n");
    	writer.flush();

    	return myMessage;
		
	}

	@Override
	public String sendMessage(GameInformationProtocol protocol, String message, PrintWriter writer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
