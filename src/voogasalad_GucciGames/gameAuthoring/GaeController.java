package voogasalad_GucciGames.gameAuthoring;

import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.gui.GAEGui;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GaeController implements IGuiGaeController, IModelGaeController{
    IGAEModel model;
    GAEGui gui;
    
    public GaeController(Stage stage){
    	System.out.println("called 1");
    	new GAEGui(this,stage);	
    }
    
    @Override
    public void addComponent (MapObject mapObj) {
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
    public void createCustomTileType (Map<String, String> m) {
        model.createCustomTileType(m);
    }
    @Override
    public void createCustomUnitType (Map<String, String> m) {
        model.createCustomUnitType(m);
    }
    @Override
    public ObservableList<MapObjectType> getImmutableTileTypes () {
        return (ObservableList<MapObjectType>) model.getImmutableTileTypes();
    }
    public ObservableList<MapObjectType> getTileTypes () {
        // TODO Auto-generated method stub
        return (ObservableList<MapObjectType>) model.getTileTypes();
    }
    @Override
    public ObservableList<MapObjectType> getImmutableUnitTypes () {
        return (ObservableList<MapObjectType>) model.getImmutableUnitTypes();
    }
    public ObservableList<MapObjectType> getUnitTypes () {
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
