package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;


public class UnitCollection {
	
	private List<MapObject> myUnits;
	
	public UnitCollection(){
		myUnits = new ArrayList<MapObject>();
	}
	
	public UnitCollection(List<MapObject> units){
		
		myUnits = units;
		
	}

	public void addAll(UnitCollection units) {
		// TODO Auto-generated method stub
		
		myUnits.addAll(units.getUnits());
		
	}
	
	
	//change this later
	public List<MapObject> getUnits(){
		return myUnits;
	}

	public Integer size() {
		return myUnits.size();
	}

	public MapObject getUnit(int n) {
		return myUnits.get(n);
	}

	public void addUnit(MapObject MapObject) {
myUnits.add(MapObject);		
	}
	
	public String toString(){
		String s = "";
		
		for(int i = 0; i < myUnits.size(); i++){
			s+= myUnits.get(i).toString() + "\n";
		}
		
		return s;
	}

}
