package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents;

import java.util.List;

public interface PlayerMapObjectInterface {
    public List<String> getAttributes(); //i.e. HP=100, Owning Player=1, ...

	public String getName();
	
	public String getImageURI();
	
	//public int getVisibility();
	
	public MapObjectBasicType getBasicType();
	
	public List<String> getActionNames();
	
	public List<TargetCoordinate> getActionTarget();
	
	public void performAction(String action, TargetCoordinate coordinate);
	
}
