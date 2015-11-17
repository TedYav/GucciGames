package voogasalad_GucciGames.gameplayer.controller;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;

// TODO: add getCoordnate(), getPlayer(), etc
public interface PlayerMapObjectInterface {
	public Map<String, String> getAttributes(); //i.e. HP=100, Owning Player=1, ...

	public String getName();

	public String getImageURI();

	public int getLayer();

	public List<String> getActionNames();

	public int getPlayerID();

	public ATargetCoordinate getCoordinate();

	public List<ATargetCoordinate> getActionTargets(String name);
}
