package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

/**
 * This class will store MapObjects. A wrapper for a MapObjectList. This class
 * supports initializing with a MapObjectCollectionsListener who will also have
 * the elements added here added to their list
 * 
 * @author Efe Aras
 *
 */
public class MapObjectCollection {

	private List<MapObject> myUnits;
	private List<MapObjectCollectionsListener> myListenerMapObjectCollections;

	public MapObjectCollection() {
		myUnits = new ArrayList<MapObject>();
		myListenerMapObjectCollections = new ArrayList<MapObjectCollectionsListener>();
	}

	public MapObjectCollection(MapObjectCollectionsListener myListener) {

		this();

		myListenerMapObjectCollections.add(myListener);
	}

	public MapObjectCollection(MapObjectCollection mapObjCollection) {
		this();

		for (MapObject m : mapObjCollection.myUnits) {
			myUnits.add(m);
		}
	}

	public void addAll(MapObjectCollection units) {
		myUnits.addAll(units.getUnits());
	}

	public List<MapObject> getUnits() {
		return myUnits;
	}

	public Integer size() {
		return myUnits.size();
	}

	public boolean addMapObject(MapObject mapObj) {
		return myUnits.add(mapObj);
	}

	public String toString() {
		String s = "";

		for (int i = 0; i < myUnits.size(); i++) {
			s += myUnits.get(i).toString() + "\n";
		}

		return s;
	}

}
