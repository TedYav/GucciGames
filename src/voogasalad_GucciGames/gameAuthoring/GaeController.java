package voogasalad_GucciGames.gameAuthoring;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import voogasalad.util.fxsprite.Sprite;
import voogasalad_GucciGames.gameAuthoring.gui.GAEGui;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.ImageBrowseDialogs;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameAuthoring.gui.levels.LevelTabPane;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.model.DisplayMapObject;
import voogasalad_GucciGames.gameAuthoring.model.GAEModel;
import voogasalad_GucciGames.gameAuthoring.model.IGameProperties;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.helpers.GameResourceManagerToGAE;
import voogasalad_GucciGames.helpers.ResourceManager;

public class GaeController extends AGuiGaeController implements IModelGaeController {

	private final IGAEModel myModel;
	private final GAEGui myGui;
	private final Stage myStage;
	private int numberOfPlayers;
	private Map<Integer, String> allPlayers = new HashMap<Integer, String>();
	private ImageBrowseDialogs myImageBrowseDialogs;
	private final GameResourceManagerToGAE myResManager = new ResourceManager();
	//private ArrayList<String> customGamePlayerComponents = new ArrayList<String>();

	public GaeController(Stage stage) {
		myStage = stage;
		myModel = new GAEModel(this);
		myGui = new GAEGui(this, stage);
	}
	
	private MapObjectType mySelectedType;

	
	public void setSelectedType(MapObjectType mapType) {
		mySelectedType = mapType;
	}

	@Override
	public MapObjectType getSelectedType() {
		return mySelectedType;
	}
	
	private MapObjectType myDragType;
	
	@Override
	public void setDragType(MapObjectType mapType) {
		myDragType = mapType;
	}

	@Override
	public MapObjectType getDragType() {
		return myDragType;
	}

	@Override
	public void deleteComponent(DisplayMapObject mapObj) {
		// TODO add levelID
		//model.deleteComponent(mapObj);
	}

	@Override
	public DisplayMapObject addObject(int levelID, GridPoint gridpoint, MapObjectType mapObjType) {
		return myModel.addObject(levelID, gridpoint, mapObjType);
	}

	@Override
	public List<DisplayMapObject> getMapObjects(int id) {
		return myModel.getMapObjects(id);
	}

	@Override
	public void clearMap(int id) {
		myModel.clearMap(id);
	}

	@Override
	public ObservableList<MapObjectType> getImmutableTileTypes() {
		return myModel.getImmutableTileTypes();
	}

	@Override
	public ObservableList<MapObjectType> getImmutableUnitTypes() {
		return myModel.getImmutableUnitTypes();
	}

	@Override
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return myModel.getImmutableStructureTypes();
	}

	@Override
	@Deprecated
	public void saveToXML(File file) {
		
	}
	
	@Override
	public void saveToXML( ){
		myModel.saveToXML();
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
		myModel.changeOwner(mapObject, playerID);

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
		return myResManager.getImage(path);
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

	@Override
	public void initGame(String name) {
		//TODO: Add the name somewhere
		myHasGameProperty.set(true);
		myGui.initGame();
		myResManager.loadGame(name);
		myResManager.toggleCopyOnAccess(true);
		myImageBrowseDialogs = new ImageBrowseDialogs(myResManager);
		//System.out.println(myResManager.getImages("units"));
	}

	@Override
	public LevelTabPane getLevelTabPane(){
		return myGui.getLevelTabPane();
	}

	@Override
	public List<String> getCustomGamePlayerComponents(String location) {
		return myModel.getComponents(location);
	}

	@Override
	public void setCustomGamePlayerComponents(String location,
			List<String> allComponents) {
		myModel.setGuiComponents(location, allComponents);
	}

	@Override
	public int addLevel(String name) {
		return myModel.addLevel(name);
	}

	
	public void setDefaultOwner(int ownerID) {
		myModel.setDefaultOwner(ownerID);
	}

	@Override
	public IGameProperties getPropertiesInterface() {
		return myModel.getPropertiesInterface();
	}

	
	private final BooleanProperty myHasGameProperty = new SimpleBooleanProperty(false);
	
	@Override
	public BooleanProperty getHasGameProperty(){
		return myHasGameProperty;
	}
	
	@Override
	public void createCustomType(MapObjectType object, String type) {
		switch (type) {
		case "tile":
			myModel.createCustomTileType(object);
			break;
		case "structure":
			myModel.createCustomStructureType(object);
			break;
		case "unit":
			myModel.createCustomUnitType(object);
			break;
		default:
			throw new RuntimeException("No "+type+" type");
		}
	}

	@Override
	public List<ActionParamsValue> getAllActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RuleParams> getAllRules() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void addActionParam() {
		// TODO Auto-generated method stub
		
	}



	public GameResourceManagerToGAE getResourceManager() {
		return myResManager;
	}

	@Override
	public Dialog<String> getImageBrowseDialog(String type) {
		return myImageBrowseDialogs.getDialog(type);
	}


}
