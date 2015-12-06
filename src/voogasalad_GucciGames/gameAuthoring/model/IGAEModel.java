package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;

import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public interface IGAEModel {

    public void deleteComponent(DisplayMapObject mapObj);
	public List<DisplayMapObject> getMapObjects(int id);
	public DisplayMapObject addObject(int levelID, GridPoint gridpoint, MapObjectType mapObjType);
	public void clearMap(int id);
    
    public void createCustomTileType(MapObjectType m);
    public void createCustomUnitType(MapObjectType m);
    public void createCustomStructureType(MapObjectType m);
    
    public ObservableList<MapObjectType> getImmutableTileTypes();
    public ObservableList<MapObjectType> getImmutableUnitTypes();
    public ObservableList<MapObjectType> getImmutableStructureTypes();

    public List<String> getComponents(String location);
    public void setGuiComponents(String location, List<String> components);
    
	public void changeOwner(MapObject mapObject, int playerID);

	public int addLevel(String name);
	
	public void saveToXML();
	
	/**
	 * Do some check!!
	 * @param ownerID
	 */
	public void setDefaultOwner(int ownerID);
	
	public List<ObjParam> getMapCharParams();
	public List<ObjParam> getSelectedMapObjCharParams(List<String> selectedChar);
	public List<ObjParam> getPlayerCharParams();
	public List<ObjParam> getSelected();
	public List<ObjParam> getOutcomes();
	public List<ObjParam> getSelectedOutcomes();
	public List<ObjParam> getConditions();
	public List<ObjParam> getSelectedConditions(List<String> selectedConditions);
	public void addActionParam(ActionParams param);
    
}
