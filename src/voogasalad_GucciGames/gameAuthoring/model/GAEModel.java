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
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
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
	private Map<Integer, GamePlayerPerson> mapOfPlayers;
	private GameInfoFactory myFactory;
	private int myOwnerID;
	
	private Map<String, ActionParams> myActions;
	private Map<String, ObjParam> myMapObjectCharacteristics;
	private Map<String, RuleParams> myRules;
	private Map<String, ObjParam> myConditions;
	private Map<String, ObjParam> myOutcomes;

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
    	//myActions = parser.getActions().stream().collect(Collectors.groupingBy(ObjParam::getName, ));
//    	Set<ActionParams> actions = parser.getActions();
//    	for (ActionParams action: actions){
//    		myActions.put(action.getName(), action);
//    	}
//    	Set<ObjParam> mapObjCharacteristcs = parser.getMapObjChars();
//    	for (ObjParam param: mapObjCharacteristcs){
//    		myMapObjectCharacteristics.put(param.getName(), param);
//    	}    
//    	Set<ObjParam> conditions = parser.getConditions();
//    	for (ObjParam param: conditions){
//    		myConditions.put(param.getName(), param);
//    	}
//    	Set<ObjParam> outcomes = parser.getOutcomes();
//    	for (ObjParam param: outcomes){
//    		myOutcomes.put(param.getName(), param);
//    	}  
//    	Set<RuleParams> rules = parser.getRules();
//    	for (RuleParams param: rules){
//    		myRules.put(param.getName(), param);
//    	}      	
    	
    	
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
    	System.err.println("IMPLEMENT ME PLZ");
    	XStreamGameEngine saver = new XStreamGameEngine();
//		AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
//		MainGameEngine engine = new MainGameEngine(myPlayers);
		//TODO: saving GameInfo instead of MainGameEngine


		//ASK ABOUT THIS, might not need engine to be passed into game info anymore

		//GameInfo game = new GameInfo();
		

		saver.saveGameInfo(game);

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


	@Override
	public List<ObjParam> getMapCharParams() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ObjParam> getSelectedMapObjCharParams(List<String> selectedChar) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ObjParam> getPlayerCharParams() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ObjParam> getSelected() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ObjParam> getOutcomes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ObjParam> getSelectedOutcomes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ObjParam> getConditions() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ObjParam> getSelectedConditions(List<String> selectedConditions) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addActionParam(ActionParams param) {
		// TODO Auto-generated method stub
		
	}
}
