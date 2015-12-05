package voogasalad_GucciGames.gameAuthoring;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.gui.GAEGui;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.gui.levels.LevelTabPane;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.model.DisplayMapObject;
import voogasalad_GucciGames.gameAuthoring.model.GAEModel;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.helpers.ImageDatabase;

public class GaeController extends AGuiGaeController implements IModelGaeController {

	private IGAEModel model;
	private GAEGui myGui;
	private MapObjectType mySelectedType;
	private MapObjectType myDragType;
	private Stage myStage;
	private int numberOfPlayers;
	private int defaultOwnerID = -1;
	private Map<Integer, String> allPlayers = new HashMap<Integer, String>();
	private ImageDatabase myImageDatabase = new ImageDatabase();
	//private ArrayList<String> customGamePlayerComponents = new ArrayList<String>();

	public GaeController(Stage stage) {
		myStage = stage;
		model = new GAEModel(this);
		myGui = new GAEGui(this, stage);
	}

	@Override
	public void deleteComponent(DisplayMapObject mapObj) {
		model.deleteComponent(mapObj);
	}

	@Override
	public DisplayMapObject addObject(GridPoint gridpoint, MapObjectType mapObjType) {
		return model.addObject(gridpoint, mapObjType, defaultOwnerID);
	}

	@Override
	public DisplayMapObject addObject(GridPoint gridpoint, MapObjectType mapObjType, int ownerID) {
		return model.addObject(gridpoint, mapObjType, ownerID);
	}

	@Override
	public List<DisplayMapObject> getMapObjects() {
		return model.getMapObjects();
	}

	@Override
	public void clearMap() {
		model.clearMap();
	}

	@Override
	public void createCustomTileType(Map<String, String> m) {
		model.createCustomTileType(m);
	}

	@Override
	public void createCustomUnitType(Map<String, String> m) {
		model.createCustomUnitType(m);
	}

	@Override
	public ObservableList<MapObjectType> getImmutableTileTypes() {
		return model.getImmutableTileTypes();
	}

	@Override
	public ObservableList<MapObjectType> getImmutableUnitTypes() {
		return model.getImmutableUnitTypes();
	}

	@Override
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return model.getImmutableStructureTypes();
	}

	@Override
	@Deprecated
	public void saveToXML(File file) {
		
	}
	
	@Override
	public void saveToXML(GameInfo game){
		model.saveToXML(game);
	}

	@Override
	public void setSelectedType(MapObjectType mapType) {
		mySelectedType = mapType;
	}

	@Override
	public MapObjectType getSelectedType() {
		return mySelectedType;
	}

	@Override
	/**
	 * create custom map object from a property object Access type via type key
	 */
	public void createCustomMapObject(ObjectProperty p) {
		// TODO do something
		// Debug:
		System.out.println("saving");
		p.printProperty();

	}

	@Override
	public void setNumberOfPlayers(int n) {
		numberOfPlayers = n;
	}

	@Override
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	@Override
	public Map<Integer, String> getAllPlayersId() {
		return Collections.unmodifiableMap(allPlayers);
	}

	@Override
	public void addPlayerToList(String name, int id) {
		allPlayers.put(id, name);
		// TODO DEBUG:
		allPlayers.forEach((k, v) -> System.out.println("k: " + k + " " + " v: " + v));

	}

	@Override
	public void changeOwner(MapObject mapObject, int playerID) {
		model.changeOwner(mapObject, playerID);

	}

	@Override
	public void savePlayer(PlayerParams playerParams) {
		// TODO Auto-generated method stub
		// TODO: DEBUG
		System.out.println("params: " + playerParams.getNumMoves());

	}

	@Override
	public void saveGameSetting(GameSettingParams gameSettingParams) {
		// TODO Auto-generated method stub
		// TODO: DEBUG
		System.out.println("params: " + gameSettingParams.getMapSize());

	}



	@Override
	public Stage getStage() {
		return myStage;

	}

	@Override
	public Image requestImage(String path) {
		return myImageDatabase.request(path);
	}

	@Override
	public ImageView getMapObjectImage(MapObjectType type) {
		double width = type.getWidth();
		double height = type.getHeight();
		double myX1 = type.getX() * width;
		double myY1 = type.getY() * height;
		Rectangle2D rect = new Rectangle2D(myX1, myY1, width, height);
		ImageView view = new ImageView(requestImage(type.getImagePath()));
		view.setViewport(rect);
		return view;
	}

	@Override
	public ImageView getMapObjectImage(DisplayMapObject object) {
		return getMapObjectImage(object.getType());
	}

	public void initGrid(int width, int height) {
		myGui.initializeMap(width, height);
	}
	
	@Override
	public LevelTabPane getLevelTabPane(){
		return myGui.getLevelTabPane();
	}

	@Override
	public List<String> getCustomGamePlayerComponents(String location) {
		return model.getComponents(location);
	}

	@Override
	public void setCustomGamePlayerComponents(String location,
			List<String> allComponents) {
		model.setGuiComponents(location, allComponents);
	}
	
	@Override
	public void addLevel(String name) {
		model.addLevel(name);
	}

	@Override
	public void setDragType(MapObjectType mapType) {
		myDragType = mapType;
	}

	@Override
	public MapObjectType getDragType() {
		return myDragType;
	}


}
