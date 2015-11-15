package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Map;
import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.GameEngineToGameAuthoringEnvironment;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.properties.TileProperty;
import voogasalad_GucciGames.gameAuthoring.properties.UnitProperty;
import voogasalad_GucciGames.gameData.XMLGameData;
import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.mapObject.DefaultMapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameData.XMLParser;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GAEModel implements IGAEModel{
    private GameSourceData data;
    private IModelGaeController myController;
    private XMLGameData xmlData;
    private GameEngineToGameAuthoringEnvironment engine;
    private GameMap map;
    
    public GAEModel(IModelGaeController controller) {
    	myController = controller;
    }

    @Override
    public boolean addComponent (Map<String,String> objParams) {
        MapObject mapObj = new MapObject(null,null, 0);// TODO:MapObject(objParams);
        if (!validate()) {
            return false;
        }
        data.addToMap(mapObj);
        return true;
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
    public void createCustomTileType (TileProperty property) throws InvalidTypeParamsException{
        try {
            MapObjectType objType = new DefaultMapObjectType(property.get("name"), property.get("imagePath"));//TODO: properties file
            data.addTileType(objType);
        }
        catch (Exception e) {
            throw new InvalidTypeParamsException("TileType parameters are invalid");
        }
    }

    @Override
    public void createCustomUnitType (UnitProperty property) throws InvalidTypeParamsException{ 
        try {
        MapObjectType objType = new DefaultMapObjectType(property.get("name"), property.get("imagePath"));//TODO: properties file
        data.addUnitType(objType);
        }
        catch (Exception e) {
            throw new InvalidTypeParamsException("UnitType parameters are invalid");
        }
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

}
