package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;

public class AllPlayers {

	// maybe put an interface in the middle?
	private List<GamePlayerPerson> myListOfPlayers;
	private int myCurrentTurn;

	public AllPlayers(List<GamePlayerPerson> players) {
		myListOfPlayers = players;
		this.myCurrentTurn = 1;
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
		this.myCurrentTurn = currentTurn;
		int whoseTurn = (currentTurn % (getNumberOfPlayers() - 1)) + 1;

		myListOfPlayers.get(whoseTurn).takeTurn();

	}

	public GamePlayerPerson getActivePlayer(int index) {
		return myListOfPlayers.get(index);
	}

	public int getCurrentTurn() {
		return this.myCurrentTurn;
	}



}
