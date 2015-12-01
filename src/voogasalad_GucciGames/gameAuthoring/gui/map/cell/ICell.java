package voogasalad_GucciGames.gameAuthoring.gui.map.cell;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public interface ICell {
	
	public boolean add(MapObject mapObject);
	
	public boolean remove(MapObject mapObject);
	
	public void clear();
	
	public void highlight();
	
	public void dehighlight();
	
	public void select();
	
	public void deselect();
	
	public boolean isSelected();
}
