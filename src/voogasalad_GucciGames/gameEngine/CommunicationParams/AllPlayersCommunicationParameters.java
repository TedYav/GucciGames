package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class AllPlayersCommunicationParameters extends CommunicationParameters {

	private AllPlayers myPlayers;

	public AllPlayersCommunicationParameters(AllPlayers players) {
		myPlayers = players;
	}

	public List<Integer> getPlayerIDList() {
		return myPlayers.getAllIds();
	}

	public GamePlayerPerson getPlayerByID(int id) {
		return myPlayers.getPlayerById(id);
	}
	public void removePlayerByID(int id){
		myPlayers.removePlayer(id);
	}

	public List<MapObject> getAllUnits() {
		return myPlayers.getAllUnits();
	}

}
