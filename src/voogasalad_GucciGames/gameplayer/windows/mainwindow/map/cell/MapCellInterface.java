package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

import javafx.geometry.Point2D;

public interface MapCellInterface {

	public void activate();
	
	public void hover();
	
	public void toggleFog(boolean fog);
	
	public void toggleHighlight(boolean highlight);
	
}
