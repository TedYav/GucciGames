package voogasalad_GucciGames.gameEngine.networking;

import java.io.BufferedReader;

@FunctionalInterface
public interface GameMessageReceiver {

	public String receiveMessage(BufferedReader in, GameNetworkRoleType gameNetworkRoleType);
	
	
}
