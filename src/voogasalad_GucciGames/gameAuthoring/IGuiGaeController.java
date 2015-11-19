package voogasalad_GucciGames.gameAuthoring;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.Property;

import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public interface IGuiGaeController extends IDialogGaeController {
	/**
	 * Add Map Object
	 * @param mapObj
	 */
    
    public void deleteComponent(MapObject mapObj);
    
    public void clearMap();
    
    public void createCustomTileType(Map<String,String> m);
    
    public void createCustomUnitType(Map<String,String> m);
    
    @Override
    public void createCustomMapObject(ObjectProperty p);

    public List<MapObjectType> getImmutableTileTypes();

    public List<MapObjectType> getImmutableUnitTypes();

    public List<MapObjectType> getTileTypes();

    public List<MapObjectType> getUnitTypes();

    public void saveToXML();
    
    public void setMapWidth(double x);
    
    public void setMapHeight(double y);
    
    public void setMapObjectTypeToMap(MapObjectType mapType);

	public MapObjectType getMapObjectTypeToMap();
	
	public Image getCurrDraggedImage();
	
	public void setCurrDraggedImage(Image draggedImage);
}
