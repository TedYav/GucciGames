package voogasalad_GucciGames.gameAuthoring;

import java.util.List;
import java.util.Map;

import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.gui.GAEGui;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GaeController implements IGuiGaeController, IModelGaeController{
    IGAEModel model;
    GAEGui gui;
    
    public GaeController(Stage stage){
    	new GAEGui(this,stage);
    }
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
    public List<MapObjectType> getTileTypes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MapObjectType> getUnitTypes () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveToXML () {
        // TODO Auto-generated method stub
        
    }
    public void addListeners() {
       // model.addObserver(gui);
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
