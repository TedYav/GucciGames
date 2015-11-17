package voogasalad_GucciGames.gameplayer.controller;

import java.util.List;

import voogasalad_GucciGames.gameplayer.controller.dummy.MapObjectBasicType;
import voogasalad_GucciGames.gameplayer.controller.dummy.TargetCoordinate;

// TODO: add getCoordnate(), getPlayer(), etc
public interface PlayerMapObjectInterface {
        public List<String> getAttributes(); //i.e. HP=100, Owning Player=1, ...

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
