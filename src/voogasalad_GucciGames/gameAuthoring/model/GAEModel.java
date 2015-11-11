package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Map;
import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.GameEngineToGameAuthoringEnvironment;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameData.XMLGameData;
import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnitType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;
import voogasalad_GucciGames.gameEngine.tile.TileType;

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
    public void addComponent (Map<String,String> objParams) {
        MapObject mapObj = new MapObject();// MapObject(objParams);
        validate();
        data.addToMap(mapObj);
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
        TileType objType = new TileType(m.get("name"), m.get("imagePath"));//TODO: properties file
        data.addTileType(objType);
    }

    @Override
    public void createCustomUnitType (Map<String, String> m) {  
        GameUnitType objType = new GameUnitType(m.get("name"), m.get("imagePath"));//TODO: properties file
        data.addUnitType(objType);
    }

    @Override
    public List<TileType> getImmutableTileTypes () {
        return data.getImmutableTileTypes();
    }

    @Override
    public List<GameUnitType> getImmutableUnitTypes () {
        return data.getImmutableUnitTypes();
    }

    @Override
    public void saveToXML () {
        //xmlData.write();
    }
    private boolean validate(){
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
}
