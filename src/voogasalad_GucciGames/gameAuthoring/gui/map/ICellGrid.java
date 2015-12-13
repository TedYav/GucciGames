package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.map.cell.ICell;

public interface ICellGrid {

	public DoubleProperty getCellSize();

	public AGuiGaeController getController();

	public boolean selectCell(ICell cell);

	public boolean deselectCell(ICell cell);

	public int getLevelID();

}
