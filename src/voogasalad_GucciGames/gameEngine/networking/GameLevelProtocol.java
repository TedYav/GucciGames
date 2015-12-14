package voogasalad_GucciGames.gameEngine.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameData.wrapper.GameEngine;

public class GameLevelProtocol extends GameInformationProtocol{

	private GameEngine myEngine;
	private String myTag;
	

	public String sendMessage(PrintWriter writer) {
		XStream xstream = new XStream(new DomDriver());
		String s = xstream.toXML(myEngine);
		
		getMessagingProtocol().sendMessage(this, s, writer);
		
		return s;
	}
	
	public String receiveMessage(Reader in, GameNetworkRoleType role){
		
		String info = "";
		try {
			info = getMessagingProtocol().receiveMessage(in);
		} catch (IOException e) {
myEngine.notifyEngine(new NetworkException("network error"));		}
		updateLevel(info);
		myEngine.refreshGUI();
		
		return info;
		
	}

	private void updateLevel(String info) {
		
	}

	@Override
	public String sendMessage(PrintWriter out, String message) {

		return sendMessage(out);
	}

	@Override
	public String receiveMessage(BufferedReader in, GameNetworkRoleType gameNetworkRoleType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendMessage(Writer out, String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String receiveMessage(Reader in) {
		// TODO Auto-generated method stub
		return null;
	}


}
