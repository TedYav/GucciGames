package voogasalad_GucciGames.gameEngine.CommunicationParameters;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;

public class ChangedParameters extends CommunicationParameters {
	private List<Integer> playersChangedIDs;
	private String level;
	private List<PlayerMapObjectInterface> changedUnits;

	public ChangedParameters() {
		changedUnits = new ArrayList<PlayerMapObjectInterface>();
		playersChangedIDs = new ArrayList<Integer>();

	}

	public void addPlayer(int id) {
		playersChangedIDs.add(id);
	}

	public List<Integer> getChangedPlayersById() {
		return playersChangedIDs;
	}

	public void addUnit(PlayerMapObjectInterface unit) {
		changedUnits.add(unit);
	}

	public List<PlayerMapObjectInterface> getChangedUnits() {
		return changedUnits;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
