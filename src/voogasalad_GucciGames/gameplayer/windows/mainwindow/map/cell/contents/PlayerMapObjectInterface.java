package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents;

import java.util.List;

// TODO: add getCoordnate(), getPlayer(), etc
public interface PlayerMapObjectInterface {

	public String getName();
	
	public String getImageURI();
	
	//public int getVisibility();
	
	public default int getLayer(){
		return 0;
	}
	
	public MapObjectBasicType getBasicType();
	
	public List<String> getActionNames();
	
	public List<TargetCoordinate> getActionTarget();
	
	public void performAction(String action, TargetCoordinate coordinate);
	
}
