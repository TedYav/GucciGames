package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.geometry.Point2D;



/**
 * interface for a game map -> passed to front end to populate list of maps
 * passed to engine to tell it to load a given map
 * @author Ted Yavuzkurt
 *
 */
public interface IGUIMap {
	
	
	public void setMapObjectForPosition(GUIMapObject obj, Point2D pos );
	
	public void removeMapObjectAtPosition(GUIMapObject obj, Point2D pos );
	

}
