package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;

/**
 * interface for a game map GUI -> for map package internal use only
 * @author Mike Ma
 *
 */
interface IGuiMap {
	
	public DoubleProperty getCellSizeProperty();
	
	public void add(Node n);
	
	public void remove(Node n);

}
