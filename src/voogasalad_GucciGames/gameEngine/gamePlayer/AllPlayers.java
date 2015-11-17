package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public class AllPlayers {

	private Map<Integer, GamePlayerPerson> myMapOfPlayers;
	private List<PlayerMapObjectInterface> myListOfAllMapObjects; //perhaps change it to a set later
	
	public AllPlayers(Map<Integer, GamePlayerPerson> players) {

		myMapOfPlayers = players;
	}

	public AllPlayers() {
		myMapOfPlayers = new HashMap<Integer, GamePlayerPerson>();
	}
	
	/***
	 * 
	 * @param id
	 * The neutral player with the game tiles (MapObject) has id -1.
	 * @return
	 */
	
	public GamePlayerPerson getPlayerById(int id){
		
		return myMapOfPlayers.get(id);
	}

	public int numberOfPlayer() {
		return myMapOfPlayers.size();
	}

	// right now includes the neutral player
	public int getNumberOfPlayers() {
		return myMapOfPlayers.size();
	}

	public GamePlayerPerson getActivePlayer(int index) {
		return myMapOfPlayers.get(index);
	}


	public void removePlayer(int id) {
		Iterator<GamePlayerPerson> itr = myMapOfPlayers.values().iterator();
		while (itr.hasNext()) {
			if (itr.next().getMyPlayerId() == id) {
				itr.remove();
				return;
			}
		}
	}

	public List<PlayerMapObjectInterface> getInitialState() {
		ArrayList<PlayerMapObjectInterface> myInitObjects = new ArrayList<PlayerMapObjectInterface>();
		
		for(GamePlayerPerson player : myMapOfPlayers.values()){
			List<MapObject> myPlayerUnits = player.getMapObjects();
			for(MapObject m : myPlayerUnits){
				myInitObjects.add((PlayerMapObjectInterface) m);
			}
		}
		
		return null;
	}

}
