package voogasalad_GucciGames.gameEngine;

import java.util.List;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.UnitCollection;

public class GameMap {
	
	private AllPlayers myGamePlayers;

	public GameMap(AllPlayers gamePlayers) {
		// TODO Auto-generated constructor stub
		myGamePlayers = gamePlayers;
		
	}

	
	/**
	 * Returns a list of all units in the game with index i reserved for the units of ith player.
	 * @return
	 */
	public List<UnitCollection> getAllUnits() {
		return myGamePlayers.getAllUnits();
	}

	public int getNumberOfPlayers() {
		return myGamePlayers.getNumberOfPlayers();
	}

	
	
	
}
