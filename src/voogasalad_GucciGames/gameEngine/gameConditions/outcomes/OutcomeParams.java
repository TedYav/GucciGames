package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sally Al
 *
 */
public class OutcomeParams {
	private Map<String, Object> myArguments = new HashMap<String, Object>();
	private List<Integer> playerID = new ArrayList<Integer>();
	private List<Integer> objectID = new ArrayList<Integer>();

	public void addArgument(String name, Object value) {
		myArguments.put(name, value);
	}

	public Object getArgumentValue(String name) {
		return myArguments.get(name);
	}

	public void setPlayerID(int id) {
		playerID.add(id);
	}

	public List<Integer> getPlayerID() {
		return playerID;
	}

	public List<Integer> getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID.add(objectID);
	}

}
