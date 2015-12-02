package voogasalad_GucciGames.gameAuthoring.gui.map.cell;

import voogasalad_GucciGames.gameAuthoring.model.DisplayMapObject;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public interface ICell {

	public boolean add(MapObjectType mapObject);

	public boolean remove(DisplayMapObject mapObject);

	public void clear();

	public void highlight();

	public void dehighlight();

	public void select();

	public void deselect();

	public boolean isSelected();
}
