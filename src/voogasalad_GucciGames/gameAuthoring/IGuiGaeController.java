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

	public void initGame(String name);

	public void deleteComponent(DisplayMapObject mapObj);

	public List<DisplayMapObject> getMapObjects(int id);

	public void clearMap(int id);

	public void createCustomTileType(Map<String, String> m);

	public void createCustomUnitType(Map<String, String> m);

	public ObservableList<MapObjectType> getImmutableTileTypes();

	public ObservableList<MapObjectType> getImmutableUnitTypes();

	public ObservableList<MapObjectType> getImmutableStructureTypes();

	@Deprecated
	public void saveToXML(File file);

	public void saveToXML();
	
	public void setSelectedType(MapObjectType mapType);

	public MapObjectType getSelectedType();
	
	public void setDragType(MapObjectType mapType);

	public MapObjectType getDragType();

	public DisplayMapObject addObject(int levelID, GridPoint gridpoint, MapObjectType mapObjType);

	public void changeOwner(MapObject mapObject, int playerID);

	public Stage getStage();

	public Image requestImage(String path);

	public ImageView getMapObjectImage(MapObjectType object);

	public ImageView getMapObjectImage(DisplayMapObject object);

	public List<String> getCustomGamePlayerComponents(String location);

	public void setCustomGamePlayerComponents(String location, List<String> allComponents);

	public LevelTabPane getLevelTabPane();

	public int addLevel(String name);


}
