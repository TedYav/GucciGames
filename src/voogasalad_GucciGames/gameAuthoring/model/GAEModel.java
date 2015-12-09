package voogasalad_GucciGames.gameAuthoring.model;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ParamObjParser;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParamsValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.model.factories.GameInfoFactory;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GuiData;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class GAEModel implements IGAEModel{
    private TypeData typeData;
    private GuiData guiData;
    private IModelGaeController myController;
	private GameInfoFactory myFactory;
	private int myOwnerID;
	


	//private List<DisplayMapObject> myMapObjects;
	// map from level id (unique) to list of map objects
	// private Map<Integer, MapData> myLevels;
	private LevelData levelData;

    
    public GAEModel(IModelGaeController controller) {
    	myController = controller;
    	typeData = new TypeData();
    	myFactory = new GameInfoFactory();
    	guiData = new GuiData();
    	levelData = new LevelData();
    	myOwnerID = 0;
    	
    	
    	// load all default properites
    	//myActions = parser.getActions().stream().collect(Collectors.groupingBy(ObjParam::getName, ));
		
    }
    

    @Override
    public void deleteComponent (int levelID, DisplayMapObject mapObj) {
//        int owner = mapObj.getOwnerID();
//        mapOfPlayers.get(owner).getMapObjects().remove(mapObj);
        levelData.deleteObject(levelID, mapObj);
    }
    
    @Override
	public List<DisplayMapObject> getMapObjects(int level) {
		return levelData.getLevelMapObjects(level);
	}

    @Override
    public void clearMap (int level) {
        levelData.clearLevelMap(level);
    }

    @Override

    public void createCustomTileType (MapObjectType m) {
    	typeData.addTileType(m);
    }

    @Override
    public void createCustomUnitType (MapObjectType m) {  
    	typeData.addUnitType(m);
    }
    
    @Override

	public void createCustomStructureType(MapObjectType m) {
    	typeData.addStructureType(m);
	}

    @Override
    public ObservableList<MapObjectType> getImmutableTileTypes () {
        return typeData.getImmutableTileTypes();
    }

    @Override
    public ObservableList<MapObjectType> getImmutableUnitTypes () {
        return typeData.getImmutableUnitTypes();
    }
    
    @Override
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return typeData.getImmutableStructureTypes();
	}
    

    private void saveToXML (GameInfo game) {    	
    	XStreamGameEngine saver = new XStreamGameEngine();
	saver.saveGameInfo(game);
    }
    
    public void saveToXML() {
//      AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
//      MainGameEngine engine = new MainGameEngine(myPlayers);
    	saveToXML(myFactory.create(typeData, levelData, guiData));
    	
    	
    }
    
    private boolean validate(){ //TODO
        return false;
    }

	@Override
	public void changeOwner(MapObject mapObject, int playerID) {
		typeData.changeOwner(mapObject, playerID);

	}
	


	@Override
	public List<String> getComponents(String location) {
		return guiData.getComponents(location);
	}


	@Override
	public void setGuiComponents(String location, List<String> components) {
		guiData.setComponents(location, components);
		
	}


	@Override
	public int addLevel(String name) {
		return levelData.addLevel(name);
		
	}


	@Override
	public DisplayMapObject addObject(int levelID, GridPoint gridpoint, MapObjectType mapObjType) {
		
		DisplayMapObject mapObj = new DisplayMapObject(mapObjType, gridpoint, levelID , mapObjType.getLayer());
		levelData.add(levelID, mapObj);
		return mapObj;
	}

	@Override
	public void setDefaultOwner(int ownerID) {
		myOwnerID = ownerID;
	}


	@Override
	public IGameProperties getPropertiesInterface() {
		return typeData;
	}
	
	@Override
	public void addPlayerCharacteristic(int playerID, ObjParamValue param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMapObjectCharacteristic(MapObjectType type, ObjParamValue param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addActionParamValue(MapObjectType type, ActionParamsValue param) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteTileType(MapObjectType object) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteStructureType(MapObjectType object) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteUnitType(MapObjectType object) {
		// TODO Auto-generated method stub
		
	}

}
