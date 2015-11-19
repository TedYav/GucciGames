package voogasalad_GucciGames.gameAuthoring.model;


import java.util.HashMap;
import java.util.Map;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameData.XMLGameData;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.DefaultMapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GAEModel implements IGAEModel{
    private GameSourceData data;
    private IModelGaeController myController;
    private XMLGameData xmlData;
    private XMLWriter writer;
    //private GameEngineToGameAuthoringEnvironment engine;
	private Map<Integer, GamePlayerPerson> mapOfPlayers;	
    private AllPlayers players;
	private MainGameEngine engine;
    
    public GAEModel(IModelGaeController controller) {
    	myController = controller;
    	data = new GameSourceData();
    	writer = new XMLWriter();
    }
    

    @Override
    public void deleteComponent (MapObject mapObj) {
        data.deleteFromMap(mapObj);
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
    public void saveToXML () {    	
    	/*
    	 * Don't instantiate these
    	 * Change to instance variables
    	 */
		Map<Integer, GamePlayerPerson> mapOfPlayers = new HashMap<Integer, GamePlayerPerson>();	
		AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
		
		MainGameEngine engine = new MainGameEngine(myPlayers);
		writer.write(engine);
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

    @Override
    public void addObserver (ListChangeListener o) {
        data.addListener(o);
    }

	@Override
	public ObservableList<MapObjectType> getTileTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableList<MapObjectType> getUnitTypes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ObservableList<MapObjectType> getStructureTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	
    public void addComponent (Map<String,String> objParams) {
        MapObject mapObj = new MapObject(null,null, 0);// TODO:MapObject(objParams);
        validate();
        data.addToMap(mapObj);
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
