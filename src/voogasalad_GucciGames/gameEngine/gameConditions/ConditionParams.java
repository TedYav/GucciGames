
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class ConditionParams {

	private String myName;
	private String myType;
	private List<Integer> myPlayersID;
	private Map<String, Object> myArgs;
	private List<GamePlayerPerson> myPlayers;
	private List<Integer> removeIDs;

	public ConditionParams(String name, String type, List<Integer> players, Map<String, Object> args) {
		myName = name;
		myType = type;
		myArgs = args;
		myPlayersID = players;
		removeIDs = new ArrayList<Integer>();

	}

	protected String getName() {
		return myName;
	}

	protected String getType() {
		return myType;
	}

	protected Map<String, Object> getArgs() {
		return myArgs;
	}

	protected List<Integer> getPlayerID() {
		return myPlayersID;

	}

	public List<GamePlayerPerson> getMyPlayers() {
		return myPlayers;
	}

	public void setMyPlayers(List<GamePlayerPerson> players) {
		myPlayers = players;
	}

	public void removePlayer() {
		if (myPlayers.size() != 0 && myPlayers != null) {
			for (int i = 0; i < removeIDs.size(); i++) {
				Iterator<GamePlayerPerson> it = myPlayers.iterator();
				while (it.hasNext()) {
					if (it.next().getMyPlayerId() == removeIDs.get(i)) {
						it.remove();
					}
				}
			}

		}
	}

	public int removeIDSize() {
		return removeIDs.size();
	}

	public void addRemoveIDs(Integer id) {
		removeIDs.add(id);
	}

}
