package voogasalad_GucciGames.gameAuthoring;

import java.util.List;
import java.util.Map;

import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.gui.GAEGui;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameAuthoring.properties.TileProperty;
import voogasalad_GucciGames.gameAuthoring.properties.UnitProperty;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GaeController implements IGuiGaeController, IModelGaeController{
    IGAEModel model;
    GAEGui gui;
    
    public GaeController(Stage stage){
    	new GAEGui(this,stage);
    }
    @Override
    public void addComponent (Map<String,String> mapObj) {
        model.addComponent(mapObj);
    }
    @Override
    public void deleteComponent (MapObject mapObj) {
        model.deleteComponent(mapObj);
    }
    @Override
    public void clearMap () {
        model.clearMap();
    }
    @Override
    public void createCustomTileType (TileProperty property) {
        model.createCustomTileType(property);
    }
    @Override
    public void createCustomUnitType (UnitProperty property) {
        model.createCustomUnitType(property);
    }
    @Override
    public List<MapObjectType> getImmutableTileTypes () {
        return model.getImmutableTileTypes();
    }
    public List<MapObjectType> getTileTypes () {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public List<MapObjectType> getImmutableUnitTypes () {
        return model.getImmutableUnitTypes();
    }
    public List<MapObjectType> getUnitTypes () {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void saveToXML () {
        model.saveToXML();
    }
    public void addListeners() {
       // model.addObserver(gui);
    }
    @Override
    public void setMapWidth (double x) {
        model.setMapWidth(x);
    }
    @Override
    public void setMapHeight (double y) {
        model.setMapHeight(y);
    }
}
