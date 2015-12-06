package voogasalad_GucciGames.gameAuthoring.model;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ParamObjParser;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GuiData;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class GAEModel implements IGAEModel{
    private TypeData typeData;
    private GuiData guiData;
    private IModelGaeController myController;
	private Map<Integer, GamePlayerPerson> mapOfPlayers;
	private GameInfoFactory myFactory;
	private int myOwnerID;
	
	private Map<String, ObjParam> myActions;
	private Map<String, ObjParam> myCharacteristics;
	private Map<String, ObjParam> myRules;
	//private List<DisplayMapObject> myMapObjects;
	// map from level id (unique) to list of map objects
	//private Map<Integer, MapData> myLevels;
	private LevelData levelData;

    
    public GAEModel(IModelGaeController controller) {
    	myController = controller;
    	typeData = new TypeData();
    	//mapData = new MapData();
    	mapOfPlayers = new HashMap<>();
    	myFactory = new GameInfoFactory();
    	//myMapObjects = new ArrayList<>();
    	guiData = new GuiData();
    	//myLevels = new HashMap<>();
    	levelData = new LevelData();
    	myOwnerID = 0;
    	
    	// load all default properites
    	ParamObjParser parser = new ParamObjParser();
    	Set<ObjParam> objs = parser.getMapObjChars();
    	objs.stream().forEach(e -> {
    		System.out.println(e.getName());
    	});
    	System.out.println("size" + objs.size());
    	
    	// Probs need to change this
		mapOfPlayers.put(-1, new GamePlayerPerson(-1));
		mapOfPlayers.put(0, new GamePlayerPerson(0));
		mapOfPlayers.put(1, new GamePlayerPerson(1));
		mapOfPlayers.put(2, new GamePlayerPerson(2));
		
    }
    

    @Override
    public void deleteComponent (DisplayMapObject mapObj) {
        int owner = mapObj.getOwnerID();
        mapOfPlayers.get(owner).getMapObjects().remove(mapObj);
    }

//    @Override
//    public DisplayMapObject addObject(int levelID, GridPoint gridpoint, MapObjectType mapObjType, int ownerID) {
//    	TargetCoordinateSingle targCoordSingle = new TargetCoordinateSingle(gridpoint.getX(), gridpoint.getY());
//    	int layer = mapObjType.isTile() ? 0 : 1;
//    	DisplayMapObject mapObject = new DisplayMapObject(mapObjType, targCoordSingle, ownerID,layer);
//    	//mapOfPlayers.get(ownerID).addMapObject(mapObject);
//    	levelData.add(levelID, mapObject);
//    	//Validate with engine, if failed, return null, else return this mapObject
//    	return mapObject;
//    }
    
    @Override
	public List<DisplayMapObject> getMapObjects(int level) {
		return levelData.getLevelMapObjects(level);
	}

    @Override
    public void clearMap (int level) {
        levelData.clearLevelMap(level);
    }

    @Override
    public void createCustomTileType (Map<String, String> m) {
    	MapObjectType objType = new DefaultMapObjectType(m.get("name"), m.get("imagePath"));//TODO: properties file
        typeData.addTileType(objType);
    }

    @Override
    public void createCustomUnitType (Map<String, String> m) {  
    	MapObjectType objType = new DefaultMapObjectType(m.get("name"), m.get("imagePath"));//TODO: properties file
        typeData.addUnitType(objType);
    }
    
    @Override
	public void createCustomStructureType(Map<String, String> m) {
    	MapObjectType objType = new DefaultMapObjectType(m.get("name"), m.get("imagePath"));//TODO: properties file
        typeData.addStructureType(objType);
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
    	System.err.println("IMPLEMENT ME PLZ");
//    	XStreamGameEngine saver = new XStreamGameEngine();
//		AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
//		MainGameEngine engine = new MainGameEngine(myPlayers);
//		//TODO: saving GameInfo instead of MainGameEngine
//		if (guiData.numberOfComponents() == 0) {
//			guiData.addLeftComponent("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectImage");
//			guiData.addLeftComponent("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectDetails");
//			guiData.addLeftComponent("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayChat");
//			guiData.addRightComponent("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.ActionDisplay");
//			guiData.addRightComponent("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.GameStatsDisplay");
//			guiData.addRightComponent("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.EndTurnButton");
//		}
//
//		//ASK ABOUT THIS, might not need engine to be passed into game info anymore
//
//		GameInfo game = new GameInfo();
//
//		saver.saveGameInfo(game, file);

    }
    
    public void saveToXML() {
    	myFactory.create(typeData, levelData, guiData);
    	
    }
    
    private boolean validate(){ //TODO
        return false;
    }

	@Override
	public void changeOwner(MapObject mapObject, int playerID) {
		int oldID = mapObject.getPlayerID(); 
		mapOfPlayers.get(oldID).getMapObjects().remove(mapObject);
		mapObject.setOwnerID(playerID);
		mapOfPlayers.get(playerID).addMapObject(mapObject);
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
}
