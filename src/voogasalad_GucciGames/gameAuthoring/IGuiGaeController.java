package voogasalad_GucciGames.gameAuthoring;

import java.util.List;
import java.util.Map;
import voogasalad_GucciGames.gameAuthoring.properties.TileProperty;
import voogasalad_GucciGames.gameAuthoring.properties.UnitProperty;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public interface IGuiGaeController {
	/**
	 * Add Map Object
	 * @param mapObj
	 */
    public void addComponent(Map<String,String> mapObj); //validate in back end

    public void deleteComponent(MapObject mapObj);

    public void clearMap();

    public void createCustomTileType(TileProperty property);

    public void createCustomUnitType(UnitProperty property);

    public List<MapObjectType> getImmutableTileTypes();

    public List<MapObjectType> getImmutableUnitTypes();

    public List<MapObjectType> getTileTypes();

    public List<MapObjectType> getUnitTypes();

    public void saveToXML();

    public void setMapWidth(double x);
    public void setMapHeight(double y);
}
