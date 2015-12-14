package voogasalad_GucciGames.gameEngine.networking;

import java.util.Set;

public interface GameNetworkEngineInterface {

	Set<Integer> getPlayerIDs();

	void notifyEngine(NetworkException networkException);

}
