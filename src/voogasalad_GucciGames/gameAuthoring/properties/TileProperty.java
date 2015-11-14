package voogasalad_GucciGames.gameAuthoring.properties;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;

public class TileProperty extends Property {
	
	private Map<String, String> map = new HashMap<String, String>();
	

	@Override
	public void setProperty(String key, String value) throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPropertyElement(String propName, String prop) throws InvalidInputException {
		// TODO Auto-generated method stub
		map.put(propName, prop);
		printProperty();
	}
	
	//Debug
	private void printProperty(){
		map.forEach((k,v) -> System.out.println("key: " + k + " value: " + v));
	}

}
