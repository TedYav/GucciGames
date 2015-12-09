package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class AllPlayers {

	private Map<Integer, GamePlayerPerson> myMapOfPlayers;
	private Map<Integer, GamePlayerPerson> myMapOFAllPlayers;
	public AllPlayers(Map<Integer, GamePlayerPerson> players) {

		myMapOfPlayers = players;
		
	}

	public AllPlayers() {
		myMapOfPlayers = new HashMap<Integer, GamePlayerPerson>();
	}

	
	public void reset() {
		for (Integer i : myMapOfPlayers.keySet()) {
			GamePlayerPerson person = myMapOfPlayers.get(i);
			person.resetObjects();
			person.getMovable().reset();

		}
	}
	
	public Map<Integer, GamePlayerPerson> getPlayersMap(){
		return this.myMapOfPlayers;
	}

	/***
	 * y
	 *
	 * @param id
	 *            The neutral player with the game tiles (MapObject) has id -1.
	 * @return
	 */

	public GamePlayerPerson getPlayerById(int id) {

		return myMapOfPlayers.get(id);
	}

	// right now includes the neutral player
	public int getNumberOfPlayers() {
		return myMapOfPlayers.size();
	}

	public GamePlayerPerson getActivePlayer(int index) {
		return myMapOfPlayers.get(index);
	}

	public void removePlayer(int id) {
		if(myMapOfPlayers.containsKey(id)&& myMapOfPlayers!=null){
			myMapOfPlayers.remove(id);
		}
	}

	public List<Integer> getAllExistingIds() {
		/*
		 * List<Integer> result = new ArrayList<>(); for(GamePlayerPerson
		 * player: this.myMapOfPlayers){ result.add(player.getMyPlayerId()); }
		 * return result;
		 */
		List<Integer> result = new ArrayList<>(this.myMapOfPlayers.keySet());
		Collections.sort(result);
		return result;
	}
	

	// make the following collections unmodifiable
	public List<PlayerMapObjectInterface> getInitialState() {
		ArrayList<PlayerMapObjectInterface> myInitObjects = new ArrayList<PlayerMapObjectInterface>();

		for (GamePlayerPerson player : myMapOfPlayers.values()) {
			System.out.println("add:" + player.getMapObjects().size());
			List<MapObject> myPlayerUnits = player.getMapObjects();
			for (MapObject m : myPlayerUnits) {
				myInitObjects.add(m);
			}
		}

		return myInitObjects;
	}

	public List<MapObject> getAllUnits() {
		// TODO Auto-generated method stub

		List<MapObject> myInitObjects = new ArrayList<MapObject>();

		for (GamePlayerPerson player : myMapOfPlayers.values()) {
			List<MapObject> myPlayerUnits = player.getMapObjects();
			for (MapObject m : myPlayerUnits) {
				myInitObjects.add(m);
			}
		}

		return myInitObjects;
	}

	public List<MapObject> getNonNeutralMapObjects(){
		List<MapObject> result = new ArrayList<MapObject>();
		this.myMapOfPlayers.keySet().stream().forEach(key -> {
			if(key != -1){
				result.addAll(this.myMapOfPlayers.get(key).getMapObjects());
			}
		});
		return result;
	}

}
