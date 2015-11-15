package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

import java.util.List;
import java.util.Map;

public interface MapCell {

	public void activate();
	
	public void hover();
	
	public void toggleFog(boolean fog);
	
	public void toggleHighlight(boolean highlight);
	
}
