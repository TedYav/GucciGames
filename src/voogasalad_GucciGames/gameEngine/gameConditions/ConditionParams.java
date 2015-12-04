
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class ConditionParams {

	private Map<String, Object> myArgs;
	private List<GamePlayerPerson> myPlayers;


	public ConditionParams(Map<String, Object> args) {
		myArgs = args;
	}

	protected Map<String, Object> getArgs() {
		return myArgs;
	}

	public List<GamePlayerPerson> getMyPlayers() {
		return myPlayers;
	}

	public void setMyPlayers(List<GamePlayerPerson> players) {
		myPlayers = players;
	}

}
