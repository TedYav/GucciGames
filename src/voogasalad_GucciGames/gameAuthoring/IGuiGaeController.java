package voogasalad_GucciGames.gameAuthoring;

import voogasalad_GucciGames.gameAuthoring.gui.levels.LevelTabPane;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.model.DisplayMapObject;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;

import java.io.File;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public interface IGuiGaeController {
	/**
	 * Add Map Object
	 * 
	 * @param mapObj
	 */

	public void initGrid(int width, int height);

	public void deleteComponent(DisplayMapObject mapObj);

	public DisplayMapObject addObject(GridPoint gridpoint, MapObjectType mapObjType);

	public List<DisplayMapObject> getMapObjects();

	// public int getMapObjectListPosAtPoint(ObservableList<MapObject>
	// mapObjectList, GridPoint gridPoint);

	public void clearMap();

	public void createCustomTileType(Map<String, String> m);

	public void createCustomUnitType(Map<String, String> m);

	public ObservableList<MapObjectType> getImmutableTileTypes();

	public ObservableList<MapObjectType> getImmutableUnitTypes();

	public ObservableList<MapObjectType> getImmutableStructureTypes();

	@Deprecated
	public void saveToXML(File file);

	public void saveToXML(GameInfo game);
	
	public void setSelectedType(MapObjectType mapType);

	public MapObjectType getSelectedType();
	
	public void setDragType(MapObjectType mapType);

	public MapObjectType getDragType();

	public DisplayMapObject addObject(GridPoint gridpoint, MapObjectType mapObjType, int ownerID);

	public void changeOwner(MapObject mapObject, int playerID);

	public Stage getStage();

	public Image requestImage(String path);

	public ImageView getMapObjectImage(MapObjectType object);

	public ImageView getMapObjectImage(DisplayMapObject object);

//
//	public List<String> getCustomGamePlayerLeftComponents();
//
//	public void setCustomGamePlayerLeftComponents(List<String> allComponents);    
//
//	public List<String> getCustomGamePlayerRightComponents();
//
//	public void setCustomGamePlayerRightComponents(List<String> allComponents);    
//
//	public List<String> getCustomGamePlayerBottomComponents();
//
//	public void setCustomGamePlayerBottomComponents(List<String> allComponents);

	public List<String> getCustomGamePlayerComponents(String location);

	public void setCustomGamePlayerComponents(String location, List<String> allComponents);

	public LevelTabPane getLevelTabPane();

	public void addLevel(String name);

}
