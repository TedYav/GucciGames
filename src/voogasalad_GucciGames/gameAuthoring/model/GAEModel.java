package voogasalad_GucciGames.gameAuthoring.model;

import java.util.List;
import java.util.Map;
import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.GameEngineToGameAuthoringEnvironment;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameData.XMLGameData;
import voogasalad_GucciGames.gameData.XMLParser;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GAEModel implements IGAEModel{
    private GameSourceData data;
    private IModelGaeController myController;
    private XMLGameData xmlData;
    private GameEngineToGameAuthoringEnvironment engine;
    
    public GAEModel(IModelGaeController controller) {
    	myController = controller;
    }

    @Override
    public void addComponent (MapObject mapObj) {
    }

    @Override
    public void deleteComponent (MapObject mapObj) {
    }

    @Override
    public void clearMap () {
    }

    @Override
    public void createCustomTileType (Map<String, String> m) {
    }

    @Override
    public void createCustomUnitType (Map<String, String> m) {  
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
    }
    private boolean validate(){
        return false;
    }

    @Override
    public void setMapWidth (double x) {
    }

    @Override
    public void setMapHeight (double y) {
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
}
