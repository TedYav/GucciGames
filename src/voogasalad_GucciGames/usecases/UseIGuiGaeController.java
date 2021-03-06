package voogasalad_GucciGames.usecases;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public interface UseIGuiGaeController {
	/**
	 * Add Map Object
	 * 
	 * @param mapObj
	 */
	public void addComponent(MapObject mapObj); // validate in back end

	public void deleteComponent(MapObject mapObj);

	public void clearMap();

	public void createCustomTileType(Map<String, String> m);

	public void createCustomUnitType(Map<String, String> m);

	public List<MapObject> getTileTypes();

	public List<MapObject> getUnitTypes();

	public void saveToXML();

	public void setMapWidth(double x);

	public void setMapHeight(double y);
}
