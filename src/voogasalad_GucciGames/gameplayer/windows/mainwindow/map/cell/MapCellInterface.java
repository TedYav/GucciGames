package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;

public interface MapCellInterface {

	public void activate();
	
	public void hover();
	
	public void toggleFog(boolean fog);
	
	public void toggleHighlight(boolean highlight);
	
	public Map<Integer,List<PlayerMapObjectInterface>> getUnits();
}
