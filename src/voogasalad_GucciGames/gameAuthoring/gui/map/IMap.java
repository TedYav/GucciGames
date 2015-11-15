package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.geometry.Point2D;



/**
 * interface for a game map -> passed to front end to populate list of maps
 * passed to engine to tell it to load a given map
 * @author Ted Yavuzkurt
 *
 */
public interface IMap {
	
	
	public void setMapObjectForPosition(CellGUI obj, Point2D pos );
	
	public void removeMapObjectAtPosition(CellGUI obj, Point2D pos );
	
	

}
