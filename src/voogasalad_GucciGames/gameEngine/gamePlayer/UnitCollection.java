package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

/**
 * This class will contain the units (and only units) of a player. It can throw
 * a NotAUnit exception if you try to add something that is not a unit.
 * 
 * @author Efe Aras
 *
 */
public class UnitCollection {

	private List<MapObject> myUnits;

	public UnitCollection() {
		myUnits = new ArrayList<MapObject>();
	}

	/**
	 * Only adds the units from a list of MapObjects
	 * @param units
	 */
	public UnitCollection(List<MapObject> units) {
		this();
		
		for(MapObject m : units){
			if(m.isUnit()){
				myUnits.add(m);
			}
		}
	}

	
	public void addAll(UnitCollection units) {
		myUnits.addAll(units.getUnits());
	}

	public List<MapObject> getUnits() {
		return myUnits;
	}

	public Integer size() {
		return myUnits.size();
	}

	public MapObject getUnit(int n) {
		return myUnits.get(n);
	}
/**
 * Adds a unit to the unit collection. Returns false if the unit is already contained in the list or if it is not a unit.
 * @param MapObject
 * @return
 */
	public boolean addUnit(MapObject mapObj) {
		if(mapObj.isUnit() && myUnits.contains(mapObj)){
		myUnits.add(mapObj);
		return true;
		}
		return false;
	}

	public String toString() {
		String s = "";

		for (int i = 0; i < myUnits.size(); i++) {
			s += myUnits.get(i).toString() + "\n";
		}

		return s;
	}

}
