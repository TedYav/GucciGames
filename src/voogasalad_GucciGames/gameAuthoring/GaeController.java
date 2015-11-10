package voogasalad_GucciGames.gameAuthoring;

import java.util.List;
import java.util.Map;
import voogasalad_GucciGames.gameAuthoring.gui.GameAuthoringEnvironmentGUI;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameEngine.gameUnit.GameUnitType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.tile.TileType;

public class GaeController implements IGuiGaeController, IModelGaeController{
    IGAEModel model;
    GameAuthoringEnvironmentGUI gui;

    @Override
    public void addComponent (MapObject mapObj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteComponent (MapObject mapObj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clearMap () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createCustomTileType (Map<String, String> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createCustomUnitType (Map<String, String> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<TileType> getTileTypes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GameUnitType> getUnitTypes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveToXML () {
        // TODO Auto-generated method stub
        
    }
    public void addListeners() {
        model.addObserver(gui);
    }

    @Override
    public void setMapWidth (double x) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setMapHeight (double y) {
        // TODO Auto-generated method stub
        
    }
}
