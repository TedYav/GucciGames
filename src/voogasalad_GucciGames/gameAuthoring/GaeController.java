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
    public void createCustomTileType (Map<String, String> m) {
        model.createCustomTileType(m);
    }
    @Override
    public void createCustomUnitType (Map<String, String> m) {
        model.createCustomUnitType(m);
    }
    @Override
    public List<TileType> getImmutableTileTypes () {
        return model.getImmutableTileTypes();
    }
    @Override
    public List<GameUnitType> getImmutableUnitTypes () {
        return model.getImmutableUnitTypes();
    }
    @Override
    public void saveToXML () {
        model.saveToXML();
    }
    public void addListeners() {
        model.addObserver(gui);
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
