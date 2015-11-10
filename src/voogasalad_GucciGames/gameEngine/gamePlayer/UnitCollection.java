package voogasalad_GucciGames.gameEngine.gamePlayer;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.gameUnit.GameUnit;

public class UnitCollection {
	
	private List<GameUnit> myUnits;
	
	public UnitCollection(){
		myUnits = new ArrayList<GameUnit>();
	}
	
	public UnitCollection(List<GameUnit> units){
		
		myUnits = units;
		
	}

	public void addAll(UnitCollection units) {
		// TODO Auto-generated method stub
		
		myUnits.addAll(units.getUnits());
		
	}
	
	
	//change this later
	public List<GameUnit> getUnits(){
		return myUnits;
	}

	public Integer size() {
		return myUnits.size();
	}

	public GameUnit getUnit(int n) {
		return myUnits.get(n);
	}

	public void addUnit(GameUnit gameUnit) {
myUnits.add(gameUnit);		
	}
	
	public String toString(){
		String s = "";
		
		for(int i = 0; i < myUnits.size(); i++){
			s+= myUnits.get(i).toString() + "\n";
		}
		
		return s;
	}

}
