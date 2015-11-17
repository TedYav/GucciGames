package voogasalad_GucciGames.gameplayer.controller;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameplayer.controller.dummy.MapObjectBasicType;
import voogasalad_GucciGames.gameplayer.controller.dummy.TargetCoordinate;

// TODO: add getCoordnate(), getPlayer(), etc
public interface PlayerMapObjectInterface {
	public Map<String, String> getAttributes(); //i.e. HP=100, Owning Player=1, ...

	public String getName();
	
	public String getImageURI();
	
	//public int getVisibility();
	
	public int getLayer();
	
	public MapObjectBasicType getBasicType();
	
	public List<String> getActionNames();
	
	public List<TargetCoordinate> getActionTargets(String action);
	
	public void performAction(String action, TargetCoordinate coordinate);
	
	public int getPlayerID();
	
}
