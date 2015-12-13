package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public abstract class GameMessagingProtocol {

	public abstract String receiveMessage(Reader in) throws IOException;
	
	public abstract String sendMessage(GameInformationProtocol protocol, String message, PrintWriter writer);
	
}
