package voogasalad_GucciGames.gameAuthoring.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameData.XMLGameData;
import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.game.GlobalGameCondition;
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
    private AllPlayers players;
    private GlobalGameCondition conditions;
    private GameMap map;
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
    public List<MapObjectType> getImmutableTileTypes () {
        return data.getImmutableTileTypes();
    }

    @Override
    public List<MapObjectType> getImmutableUnitTypes () {
        return data.getImmutableUnitTypes();
    }
  /*  @Override
    public List<TileType> getTileTypes () {
        return null;
    }

    @Override
    public List<GameUnitType> getUnitTypes () {
        return null;
    } */

    @Override
    public void saveToXML () {
    	
		Map<Integer, GamePlayerPerson> mapOfPlayers = new HashMap<Integer, GamePlayerPerson>();	
//		List<GamePlayerPerson> listOfPlayers = new ArrayList<GamePlayerPerson>();	
		AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
//		GameMap myMap = new GameMap(myPlayers);
//		PlayerUnitCondition myRule = new PlayerUnitCondition(listOfPlayers, null); 
//		OnlyOnePlayerHasUnitsCondition myRule = new OnlyOnePlayerHasUnitsCondition(myMap);
		MainGameEngine engine = new MainGameEngine(myPlayers, null, null);
		writer.write(engine);
    	
        //xmlData.write();
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
	public List<MapObjectType> getTileTypes() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<MapObjectType> getUnitTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//implemet this
	public void addComponent(MapObject mapObj) {
		// TODO Auto-generated method stub
		
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
