package voogasalad_GucciGames.gameAuthoring.model;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
//import voogasalad_GucciGames.gameData.XMLWriter;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.DefaultMapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class GAEModel implements IGAEModel{
    private TypeData typeData;
    private MapData mapData;
    private IModelGaeController myController;
	private Map<Integer, GamePlayerPerson> mapOfPlayers;	
    private AllPlayers players;
	private MainGameEngine engine;
    
    public GAEModel(IModelGaeController controller) {
    	myController = controller;
    	typeData = new TypeData();
    	mapData = new MapData();
    	mapOfPlayers = new HashMap<>();
    	// Probs need to change this
		mapOfPlayers.put(-1, new GamePlayerPerson(-1));
		mapOfPlayers.put(0, new GamePlayerPerson(0));
		mapOfPlayers.put(1, new GamePlayerPerson(1));
		mapOfPlayers.put(2, new GamePlayerPerson(2));

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
    	mapOfPlayers.get(ownerID).addMapObject(mapObject);
    	//Validate with engine, if failed, return null, else return this mapObject
    	return mapObject;
    }
    
    @Override
	public List<MapObject> getMapObjects() {
		return mapData.getMapObjects();
	}

    @Override
    public void clearMap () {
        mapData.clearMap();
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

    @Override
<<<<<<< HEAD

    public void saveToXML (File file) {    	
    	/*
    	 * Don't instantiate these
    	 * Change to instance variables
    	 */
=======
    public void saveToXML (File file) {    	
>>>>>>> dc0b272f4e422d353cc7597fb5ade2c3c753600e
    	XStreamGameEngine saver = new XStreamGameEngine();
		AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
		MainGameEngine engine = new MainGameEngine(myPlayers);
		saver.saveEngine(engine, file);
    }
    public void saveToXML(String filePath) {
        
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
        mapData.addToMap(mapObj);
    }


	@Override
	public void changeOwner(MapObject mapObject, int playerID) {
		int oldID = mapObject.getPlayerID(); 
		mapOfPlayers.get(oldID).getMapObjects().remove(mapObject);
		mapObject.setOwnerID(playerID);
		mapOfPlayers.get(playerID).addMapObject(mapObject);
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
