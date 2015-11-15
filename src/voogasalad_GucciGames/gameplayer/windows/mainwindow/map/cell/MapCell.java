package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

public interface MapCell {

	public void activate();
	
	public void hover();
	
	public void toggleFog(boolean fog);
	
	public void toggleHighlight(boolean highlight);
	
}
