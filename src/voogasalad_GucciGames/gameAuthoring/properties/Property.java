package voogasalad_GucciGames.gameAuthoring.properties;

import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;

public abstract class Property {
	/**
	 * Specify the type of the map object
	 * 
	 * @param s
	 */
	public abstract void setProperty(String key, String value) throws InvalidInputException;

	public abstract void addPropertyElement(String propName, String prop) throws InvalidInputException;

	// Debug
	protected void printProperty(Map map) {
		map.forEach((k, v) -> System.out.println("key: " + k + " value: " + v));
	}

}
