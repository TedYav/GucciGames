/**
 *
 */
package voogasalad_GucciGames.gameEngine.gameConditions.oucomes;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;

/**
 *
 * @author Sally Al
 *
 */
public class Outcome {// add interface?
	private AllPlayers myPlayers;
	private RemovePlayer removePlayer;

	public Outcome(AllPlayers players) {
		myPlayers = players;
		removePlayer = new RemovePlayer(players);

	}

	public void removePlayer(int id) {
		removePlayer.removePlayer(id);

	}

}
