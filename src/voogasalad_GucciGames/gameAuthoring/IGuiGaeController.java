package voogasalad_GucciGames.gameAuthoring;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.Property;

import java.io.File;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public interface IGuiGaeController{
	/**
	 * Add Map Object
	 * @param mapObj
	 */
    
    public void deleteComponent(MapObject mapObj);
    
    public MapObject addObject(GridPoint gridpoint, MapObject mapObjType);
    
    public List<MapObject> getMapObjects();
    
//    public int getMapObjectListPosAtPoint(ObservableList<MapObject> mapObjectList, GridPoint gridPoint);
    
    public void clearMap();
    
    public void createCustomTileType(Map<String,String> m);
    
    public void createCustomUnitType(Map<String,String> m);

    public ObservableList<MapObject> getImmutableTileTypes();

    public ObservableList<MapObject> getImmutableUnitTypes();
    
    public ObservableList<MapObject> getImmutableStructureTypes();

    public void saveToXML(File file);
    
    public void setMapWidth(double x);
    
    public void setMapHeight(double y);
    
    public void setMapObjectTypeToMap(MapObject mapType);

	public MapObject getMapObjectTypeToMap();
	
	public Image getCurrSelectedImage();
	
	public void setCurrDraggedImage(Image draggedImage);

	public MapObject addObject(GridPoint gridpoint, MapObject mapObjType,
			int ownerID);
	
	public void changeOwner(MapObject mapObject, int playerID);
	
	public Stage getStage();
}
