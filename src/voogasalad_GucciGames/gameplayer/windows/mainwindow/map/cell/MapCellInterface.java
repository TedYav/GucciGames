package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;

public interface MapCellInterface {

	public void activate();
	
	public void deactivate();
		
	public void toggleFog(boolean fog);
	
	public void toggleHighlight(boolean highlight);
	
	public Map<Integer,List<PlayerMapObjectInterface>> getUnits();
	
	public void addObject(PlayerMapObjectInterface object);
	public void removeObject(PlayerMapObjectInterface object);
		
	public void addObserver(Observer o);

	public Color getColor();
}
