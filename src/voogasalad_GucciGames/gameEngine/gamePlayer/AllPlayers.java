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
	
	/***
	 * 
	 * @param id
	 * The neutral player with the game tiles (MapObject) has id -1.
	 * @return
	 */
	
	public GamePlayerPerson getPlayerById(int id){
		for(GamePlayerPerson player: myListOfPlayers){
			if(player.getMyPlayerId()==id){
				return player;
			}
		}
		return null;
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

	public int getCurrentTurn() {
		
		return myListOfPlayers.get(this.myCurrentTurnCounter.getCurrentTurn()).getMyPlayerId();
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
