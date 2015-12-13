package voogasalad_GucciGames.gameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

public abstract class GameInformationProtocol extends GameProtocol implements GameMessageSender, GameMessageReceiver{
	
	private GameMessagingProtocol myProtocol;
	
	public GameInformationProtocol(GameMessagingProtocol protocol){
		this();
		myProtocol = protocol;
	}

	public GameInformationProtocol() {
		myProtocol = new DefaultMessagingProtocol();
	}

	public String getTag(){
		
		//put resource bundle stuff here
		
		return "";
	}
	
	public abstract String sendMessage(Writer out, String message);

	public abstract String receiveMessage(Reader in);

	protected GameMessagingProtocol getMessagingProtocol() {
		return myProtocol;
	}


}
