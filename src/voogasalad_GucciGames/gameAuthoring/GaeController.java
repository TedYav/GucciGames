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
import voogasalad_GucciGames.datastructures.ImageDatabase;
import voogasalad_GucciGames.gameAuthoring.gui.GAEGui;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.StructureParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.TileParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.UnitParams;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.model.GAEModel;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;



public class GaeController extends AGuiGaeController implements IModelGaeController{

	private IGAEModel model;
	private GAEGui gui;
	private MapObject mapobjecttype;
	private Image currDraggedImage;
	private Stage myStage;
    private int numberOfPlayers;
    private int defaultOwnerID = -1;
    private Map<Integer, String> allPlayers = new HashMap<Integer, String>();
    private ImageDatabase myImageDatabase = new ImageDatabase();
    
    public GaeController(Stage stage){
    	myStage = stage;
    	model = new GAEModel(this);
    	gui = new GAEGui(this,stage);	
    }
    @Override
    public void deleteComponent (MapObject mapObj) {
        model.deleteComponent(mapObj);
    }
    @Override
    public MapObject addObject(GridPoint gridpoint, MapObject mapObjType){
    	return model.addObject(gridpoint, mapObjType, defaultOwnerID);
    }
    @Override
    public MapObject addObject(GridPoint gridpoint, MapObject mapObjType, int ownerID){
    	return model.addObject(gridpoint, mapObjType, ownerID);
    }
    @Override
    public List<MapObject> getMapObjects() {
        return model.getMapObjects();
    }
//    @Override
//    public int getMapObjectListPosAtPoint(ObservableList<MapObject> mapObjectList, GridPoint gridPoint) {
//    	for(int i=0; i<mapObjectList.size(); i++){
//    		MapObject currMapObj= mapObjectList.get(i);
//    		ATargetCoordinate targCoordinate = currMapObj.getCoordinate();
//    		for(TargetCoordinateSingle targCoorSingle : targCoordinate.getListOfCoordinates()){
//	    		if (gridPoint.getX() == targCoorSingle.getCenterX() && gridPoint.getY() == targCoorSingle.getCenterY()){
//	    			return i;
//	    		}
//    		}
//    	}
//        return -1;
//    }
    @Override
    public void clearMap () {
        model.clearMap();
    }
    @Override
    public void createCustomTileType (Map<String, String> m) {
        model.createCustomTileType(m);
    }
    @Override
    public void createCustomUnitType (Map<String, String> m) {
        model.createCustomUnitType(m);
    }
    @Override
    public ObservableList<MapObject> getImmutableTileTypes () {
        return model.getImmutableTileTypes();
    }

    @Override
    public ObservableList<MapObject> getImmutableUnitTypes () {
        return model.getImmutableUnitTypes();
    }

    
    @Override
	public ObservableList<MapObject> getImmutableStructureTypes() {
		return model.getImmutableStructureTypes();
	}
	
    @Override
    public void saveToXML (File file) {
        model.saveToXML(file);
    }

    
    @Override
    public void setMapWidth (double x) {
        model.setMapWidth(x);
    }
    @Override
    public void setMapHeight (double y) {
        model.setMapHeight(y);
    }

	@Override
	public void setMapObjectTypeToMap(MapObject mapType) {
		mapobjecttype = mapType;
	}
	
	@Override
	public MapObject getMapObjectTypeToMap() {
		return mapobjecttype;
	}

	@Override
	public Image getCurrSelectedImage() {
		return currDraggedImage;
	}

	@Override
	public void setCurrDraggedImage(Image draggedImage) {
		currDraggedImage = draggedImage;	
	}
    
	@Override
	/**
	 * create custom map object from a property object
	 * Access type via type key
	 */
	public void createCustomMapObject(ObjectProperty p) {
		//TODO do something
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
		//TODO DEBUG:
		allPlayers.forEach((k, v) -> System.out.println("k: " + k + " " + " v: " + v));
		
	}
	@Override
	public void changeOwner(MapObject mapObject, int playerID) {
		model.changeOwner(mapObject, playerID);
		
	}

	@Override
	public void savePlayer(PlayerParams playerParams) {
		// TODO Auto-generated method stub
		//TODO: DEBUG
		System.out.println("params: " + playerParams.getNumMoves());
		
	}

	@Override
	public void saveGameSetting(GameSettingParams gameSettingParams) {
		// TODO Auto-generated method stub
		//TODO: DEBUG
		System.out.println("params: " + gameSettingParams.getMapSize());
		
	}
	@Override
	public void setTileParams(TileParams params) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setUnitParams(UnitParams params) {
		// TODO Auto-generated method stub
		//TODO: DEBUG
		System.out.println("set");
		params.print();
		
		
	}
	@Override
	public void setStructureParams(StructureParams params) {
		// TODO Auto-generated method stub
		
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
	public ImageView getMapObjectImage(MapObject object) {
		double width = object.getWidth();
		double height = object.getHeight();
		
		double myX1 = object.getX()*width;
		double myY1 = object.getY()*height;
		
		Rectangle2D rect = new Rectangle2D(myX1, myY1, width, height);
		
		ImageView view = new ImageView(requestImage(object.getImageURI()));
		view.setViewport(rect);
		return view;
	}
}
