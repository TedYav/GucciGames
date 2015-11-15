package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AllPlayers {

	// maybe put an interface in the middle?
	// maybe use a map here instead

	private List<GamePlayerPerson> myListOfPlayers; // Efe, I suggest that we
													// change this list to a
													// map, and use player id as
													// a key
	private TurnCounter myCurrentTurnCounter;

	private ATurnDecider myTurnDecider;

	public AllPlayers(List<GamePlayerPerson> players) {

		myListOfPlayers = players;
		myCurrentTurnCounter = new TurnCounter();
		myTurnDecider = new DefaultTurnDecider(getNumberOfPlayers(), myCurrentTurnCounter);

	}

	public int numberOfPlayer() {
		return myListOfPlayers.size();
	}

	public List<UnitCollection> getAllUnits() {

		List<UnitCollection> allUnits = new ArrayList<UnitCollection>();
		for (GamePlayerPerson player : myListOfPlayers) {
			allUnits.add(player.getUnits());
		}
		return allUnits;
	}

	// right now includes the neutral player
	public int getNumberOfPlayers() {
		return myListOfPlayers.size();
	}

	public void takeTurn(int currentTurn) {

		// account for cases where a player gets skipped

		myListOfPlayers.get(myTurnDecider.decideTurn()).takeTurn();

	}

	public GamePlayerPerson getActivePlayer(int index) {
		return myListOfPlayers.get(index);
	}

	public int getCurrentTurn() {
		return this.myCurrentTurnCounter.getCurrentTurn();
	}

	public void removePlayer(int id) {
		Iterator<GamePlayerPerson> itr = myListOfPlayers.iterator();
		while (itr.hasNext()) {
			if (itr.next().getMyPlayerId() == id) {
				itr.remove();
				return;
			}
		}
	}

}
