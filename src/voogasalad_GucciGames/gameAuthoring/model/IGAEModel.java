package voogasalad_GucciGames.gameAuthoring.model;

import java.io.File;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public interface IGAEModel {

    public void deleteComponent(DisplayMapObject mapObj);
	public List<DisplayMapObject> getMapObjects(int id);
	public DisplayMapObject addObject(int levelID, GridPoint gridpoint, MapObjectType mapObjType);
	public void clearMap(int id);
    
    public void createCustomTileType(Map<String,String> m);
    public void createCustomUnitType(Map<String,String> m);
    
    public ObservableList<MapObjectType> getImmutableTileTypes();
    public ObservableList<MapObjectType> getImmutableUnitTypes();
    public ObservableList<MapObjectType> getImmutableStructureTypes();

    public List<String> getComponents(String location);
//    public List<String> getRightComponents();
//    public List<String> getBottomComponents();
//    public void setLeftComponents(List<String> components);
//    public void setRightComponents(List<String> components);
//    public void setBottomComponents(List<String> components);
    public void setGuiComponents(String location, List<String> components);
    
	public void changeOwner(MapObject mapObject, int playerID);

	public int addLevel(String name);
	
	public void saveToXML(GameInfo game);

    
}
