package voogasalad_GucciGames.gameAuthoring;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.Property;

import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public interface IGuiGaeController{
	/**
	 * Add Map Object
	 * @param mapObj
	 */
    
    public void deleteComponent(MapObject mapObj);
    
    public MapObject addObject(GridPoint gridpoint, MapObjectType mapObjType);
    
    public List<MapObject> getMapObjects();
    
//    public int getMapObjectListPosAtPoint(ObservableList<MapObject> mapObjectList, GridPoint gridPoint);
    
    public void clearMap();
    
    public void createCustomTileType(Map<String,String> m);
    
    public void createCustomUnitType(Map<String,String> m);

    public ObservableList<MapObjectType> getImmutableTileTypes();

    public ObservableList<MapObjectType> getImmutableUnitTypes();
    
    public ObservableList<MapObjectType> getImmutableStructureTypes();

    public void saveToXML();
    
    public void setMapWidth(double x);
    
    public void setMapHeight(double y);
    
    public void setMapObjectTypeToMap(MapObjectType mapType);

	public MapObjectType getMapObjectTypeToMap();
	
	public Image getCurrSelectedImage();
	
	public void setCurrDraggedImage(Image draggedImage);

	public MapObject addObject(GridPoint gridpoint, MapObjectType mapObjType,
			int ownerID);
}
