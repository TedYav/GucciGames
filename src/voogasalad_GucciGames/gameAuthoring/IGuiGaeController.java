package voogasalad_GucciGames.gameAuthoring;

import java.util.List;
import java.util.Map;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public interface IGuiGaeController {

    public void addComponent(Map<String,String> mapObj); //validate in back end

    public void deleteComponent(MapObject mapObj);
    
    public void clearMap();
    
    public void createCustomTileType(Map<String,String> m);
    
    public void createCustomUnitType(Map<String,String> m);


    public List<MapObjectType> getImmutableTileTypes();

    public List<MapObjectType> getImmutableUnitTypes();

    public List<MapObjectType> getTileTypes();

    public List<MapObjectType> getUnitTypes();

    public void saveToXML();
    
    public void setMapWidth(double x);
    public void setMapHeight(double y);
}
