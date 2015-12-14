package voogasalad_GucciGames.gameEngine.networking;

import java.io.PrintWriter;

@FunctionalInterface
public interface GameMessageSender {

	public String sendMessage(PrintWriter out, String message);

	
}
