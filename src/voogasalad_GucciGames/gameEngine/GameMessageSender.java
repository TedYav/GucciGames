package voogasalad_GucciGames.gameEngine;

import java.io.PrintWriter;

@FunctionalInterface
public interface GameMessageSender {

	public String sendMessage(PrintWriter out, String message);

	
}
