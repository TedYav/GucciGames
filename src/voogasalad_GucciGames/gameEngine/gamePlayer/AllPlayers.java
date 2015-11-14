package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;

public class AllPlayers {


	//maybe put an interface in the middle?
	private List<GamePlayerPerson> myListOfPlayers;

	public AllPlayers(List<GamePlayerPerson> players){
		myListOfPlayers = players;
	}

	public List<UnitCollection> getAllUnits() {

		List<UnitCollection> allUnits = new ArrayList<UnitCollection>();

		for(GamePlayerPerson player : myListOfPlayers){

			allUnits.add(player.getUnits());

		}
		return allUnits;
	}

	//right now includes the neutral player
	public int getNumberOfPlayers() {
		return myListOfPlayers.size();
	}

	public void takeTurn(int currentTurn) {

		int whoseTurn = (currentTurn % (getNumberOfPlayers() - 1)) + 1;

		myListOfPlayers.get(whoseTurn).takeTurn();
		// TODO Auto-generated method stub

	}

	public GamePlayerPerson getActivePlayer(int index){
		return myListOfPlayers.get(index);
	}


}
