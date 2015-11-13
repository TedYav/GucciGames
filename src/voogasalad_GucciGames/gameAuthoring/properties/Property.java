package voogasalad_GucciGames.gameAuthoring.properties;

import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;

public abstract class Property {
	/**
	 * Specify the type of the map object
	 * @param s
	 */
	public abstract void setProperty(String key, String value) throws InvalidInputException;
	
	public abstract void addPropertyElement(String propName, String prop)throws InvalidInputException;
	
	
	
	

}
