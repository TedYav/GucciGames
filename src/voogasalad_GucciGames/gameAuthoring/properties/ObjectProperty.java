package voogasalad_GucciGames.gameAuthoring.properties;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;

public class ObjectProperty {

	private Map<String, String> myMap = new HashMap<String, String>();

	public void addPropertyElement(String propName, String prop) throws InvalidInputException {
		// TODO Auto-generated method stub
		myMap.put(propName, prop);
		printProperty();
	}

	public void printProperty() {
		myMap.forEach((k, v) -> System.out.println("key: " + k + " value: " + v));
	}

}
