package voogasalad_GucciGames.gameEngine.gameRule.oucomes;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;

/**
 *
 * @author Sally Al
 *
 */
public class RemovePlayer {
	AllPlayers myPlayers;

	public RemovePlayer(AllPlayers players) {
		myPlayers = players;
	}

	protected void removePlayer(int id){
		myPlayers.removePlayer(id);
	}

}
