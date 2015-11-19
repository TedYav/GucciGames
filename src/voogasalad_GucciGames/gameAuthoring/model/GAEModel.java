package voogasalad_GucciGames.gameAuthoring.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.UnitParams;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
//import voogasalad_GucciGames.gameData.XMLWriter;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.RealHealthCharacteristic;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.DefaultMapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class GAEModel implements IGAEModel{
    private GameSourceData data;
    private IModelGaeController myController;
    //private XMLWriter writer;
    //private GameEngineToGameAuthoringEnvironment engine;
	private Map<Integer, GamePlayerPerson> mapOfPlayers;
	private Map<String, UnitParams> unitCharacteristicMap;
    private AllPlayers players;
	private MainGameEngine engine;
    
    public GAEModel(IModelGaeController controller) {
    	myController = controller;
    	data = new GameSourceData();
    	mapOfPlayers = new HashMap<>();
    	// Probs need to change this
		mapOfPlayers.put(-1, new GamePlayerPerson(-1));
		mapOfPlayers.put(0, new GamePlayerPerson(0));
		mapOfPlayers.put(1, new GamePlayerPerson(1));
		mapOfPlayers.put(2, new GamePlayerPerson(2));
		unitCharacteristicMap = new HashMap<>();

    	//writer = new XMLWriter();
    }
    

    @Override
    public void deleteComponent (MapObject mapObj) {
        int owner = mapObj.getPlayerID();
        mapOfPlayers.get(owner).getMapObjects().remove(mapObj);
    }
    
    @Override
    public MapObject addObject(GridPoint gridpoint, MapObjectType mapObjType, int ownerID) {
    	TargetCoordinateSingle targCoordSingle = new TargetCoordinateSingle(gridpoint.getX(), gridpoint.getY());
    	int layer = mapObjType.isTile() ? 0 : 1;
    	MapObject mapObject = new MapObject(mapObjType, targCoordSingle, ownerID,layer);
    	System.out.println(mapObjType.getName());
    	
    	// Goes in resource bundle
    	UnitParams param = unitCharacteristicMap.get(mapObjType.getName());
    	mapObject.addCharacteristic("AttackCharacteristic", new AttackCharacteristic(param.getRangeAttack(), 
    			param.getDamage(), param.getNumberOfAttacks()));
    	mapObject.addCharacteristic("RealHealthCharacteristic", new RealHealthCharacteristic(param.getHealth()));
    	mapObject.addCharacteristic("MovableCharacteristic", new MovableCharacteristic(param.getRangeMvt(), 5));
    	
    	//mapObject.addCharacteristic("Attack", new AttackCharacteristic());
    	mapOfPlayers.get(ownerID).addMapObject(mapObject);
    	//Validate with engine, if failed, return null, else return this mapObject
    	return mapObject;
    }
    
    @Override
	public List<MapObject> getMapObjects() {
		return data.getMapObjects();
	}

    @Override
    public void clearMap () {
        data.clearMap();
    }

    @Override
    public void createCustomTileType (Map<String, String> m) {
        MapObjectType objType = new DefaultMapObjectType(m.get("name"), m.get("imagePath"));//TODO: properties file
        data.addTileType(objType);
    }

    @Override
    public void createCustomUnitType (Map<String, String> m) {  
        MapObjectType objType = new DefaultMapObjectType(m.get("name"), m.get("imagePath"));//TODO: properties file
        data.addUnitType(objType);
    }

    @Override
    public ObservableList<MapObjectType> getImmutableTileTypes () {
        return data.getImmutableTileTypes();
    }

    @Override
    public ObservableList<MapObjectType> getImmutableUnitTypes () {
        return data.getImmutableUnitTypes();
    }
    
    @Override
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return data.getImmutableStructureTypes();
	}
    
  /*  @Override
    public ObservableList<TileType> getTileTypes () {
        return null;
    }

    @Override
    public ObservableList<GameUnitType> getUnitTypes () {
        return null;
    } */

    @Override
    public void saveToXML (String filename) {    	
    	/*
    	 * Don't instantiate these
    	 * Change to instance variables
    	 */
    	XStreamGameEngine saver = new XStreamGameEngine();
		AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
		MainGameEngine engine = new MainGameEngine(myPlayers);
		saver.saveEngine(engine, filename);
    }
    
    private boolean validate(){ //TODO
        return false;
    }

    @Override
    public void setMapWidth (double x) {
        //map.setWidth(x);
    }

    @Override
    public void setMapHeight (double y) {
        //map.setHeight(y);
    }


    public void addComponent (Map<String,String> objParams) {
        MapObject mapObj = new MapObject(null,null, 0);// TODO:MapObject(objParams);
        validate();
        data.addToMap(mapObj);
    }


	@Override
	public void changeOwner(MapObject mapObject, int playerID) {
		int oldID = mapObject.getPlayerID(); 
		mapOfPlayers.get(oldID).getMapObjects().remove(mapObject);
		mapObject.setOwnerID(playerID);
		mapOfPlayers.get(playerID).addMapObject(mapObject);
	}


	@Override
	public void addPlayer(PlayerParams playerParams) {
		// TODO: instantiate additional player properties
		mapOfPlayers.put(playerParams.getId(), new GamePlayerPerson(playerParams.getId()));
	}


	@Override
	public void setUnitCharacteristics(UnitParams param) {
		System.out.println(param.getName().length());
		unitCharacteristicMap.put(param.getName(), param);
	}
    
//	public static void main(String[] args){
//		Map<Integer, GamePlayerPerson> mapOfPlayers = new HashMap<Integer, GamePlayerPerson>();	
////		List<GamePlayerPerson> listOfPlayers = new ArrayList<GamePlayerPerson>();	
//		AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
//		MainGameEngine engine = new MainGameEngine(myPlayers, null, null);
//		System.out.println("made game engine");
//		XMLWriter writer = new XMLWriter();
//		writer.write(engine);
//		
//	}


}
