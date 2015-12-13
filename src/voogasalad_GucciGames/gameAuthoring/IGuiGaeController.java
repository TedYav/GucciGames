package voogasalad_GucciGames.gameAuthoring;

import java.io.File;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.levels.LevelTabPane;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.model.DisplayMapObject;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import voogasalad_GucciGames.helpers.GameResourceManagerToGAE;

public interface IGuiGaeController {

	public void initGame(String name);

	public void deleteComponent(DisplayMapObject mapObj, int levelID);

	public List<DisplayMapObject> getMapObjects(int id);

	public void clearMap(int id);

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

	public Stage getStage();

	public ImageView requestImage(String path);

	public ImageView getMapObjectImage(MapObjectType object);

	public ImageView getMapObjectImage(DisplayMapObject object);

	public List<String> getCustomGamePlayerComponents(String location);

	public void setCustomGamePlayerComponents(String location, List<String> allComponents);

	public LevelTabPane getLevelTabPane();

	public int addLevel(String name, int width, int height);

	public void setDefaultOwner(int ownerID);

	public BooleanProperty getHasGameProperty();

	public GameResourceManagerToGAE getResourceManager();

	public Dialog<String> getImageBrowseDialog(String type);

	public void throwException(Exception e);

	public void addPlayerCharacteristic(int playerID, ObjParamValue param);

	public IntegerProperty getNumberOfPlayersProperty();

	public int getDefaultOwner();

	void initModel();

}
