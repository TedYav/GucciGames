package voogasalad_GucciGames.gameAuthoring;

import java.util.List;
import java.util.Map;
import voogasalad_GucciGames.gameAuthoring.model.InvalidTypeParamsException;
import voogasalad_GucciGames.gameAuthoring.properties.TileProperty;
import voogasalad_GucciGames.gameAuthoring.properties.UnitProperty;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public interface IGuiGaeController {
	/**
	 * Add Map Object
	 * @param mapObj
	 */
    public void addComponent(MapObject mapObj); //validate in back end
    
    public void deleteComponent(MapObject mapObj);
    
    public void clearMap();

    public void createCustomTileType(TileProperty property) throws InvalidTypeParamsException;

    public void createCustomUnitType(UnitProperty property) throws InvalidTypeParamsException;

    public ObservableList<MapObjectType> getImmutableTileTypes();

    public ObservableList<MapObjectType> getImmutableUnitTypes();

    public ObservableList<MapObjectType> getTileTypes();

    public ObservableList<MapObjectType> getUnitTypes();

    public void saveToXML();
    
    public void setMapWidth(double x);
    public void setMapHeight(double y);
}
