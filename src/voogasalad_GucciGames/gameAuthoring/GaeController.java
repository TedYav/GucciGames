package voogasalad_GucciGames.gameAuthoring;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;



public class GaeController extends AGuiGaeController implements IModelGaeController{

	private IGAEModel model;
	private GAEGui gui;
	private MapObjectType mapobjecttype;
	private Image currDraggedImage;
	private Stage myStage;
    private int numberOfPlayers;
    private int defaultOwnerID = -1;
    private Map<Integer, String> allPlayers = new HashMap<Integer, String>();
    
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
    public MapObject addObject(GridPoint gridpoint, MapObjectType mapObjType){
    	return model.addObject(gridpoint, mapObjType, defaultOwnerID);
    }
    @Override
    public MapObject addObject(GridPoint gridpoint, MapObjectType mapObjType, int ownerID){
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
    public ObservableList<MapObjectType> getImmutableTileTypes () {
        return model.getImmutableTileTypes();
    }

    @Override
    public ObservableList<MapObjectType> getImmutableUnitTypes () {
        return model.getImmutableUnitTypes();
    }

    
    @Override
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
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
	public void setMapObjectTypeToMap(MapObjectType mapType) {
		mapobjecttype = mapType;
	}
	
	@Override
	public MapObjectType getMapObjectTypeToMap() {
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
				System.out.println("params: " + params.getName());
		
		
	}
	@Override
	public void setStructureParams(StructureParams params) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Stage getStage() {
		return myStage;
		
	}

}
