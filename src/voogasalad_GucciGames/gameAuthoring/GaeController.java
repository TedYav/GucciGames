package voogasalad_GucciGames.gameAuthoring;

import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.gui.GAEGui;
import voogasalad_GucciGames.gameAuthoring.model.GAEModel;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import voogasalad_GucciGames.gameAuthoring.properties.Property;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

public class GaeController implements IGuiGaeController, IModelGaeController{
    IGAEModel model;
    GAEGui gui;
    MapObjectType mapobjecttype;
    Image currDraggedImage;
    
    public GaeController(Stage stage){
    	System.out.println("called 1");
    	model = new GAEModel(this);
    	gui = new GAEGui(this,stage);	
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
        return model.getImmutableTileTypes();
    }
    public ObservableList<MapObjectType> getTileTypes () {
        return model.getTileTypes();
    }
    @Override
    public ObservableList<MapObjectType> getImmutableUnitTypes () {
        return model.getImmutableUnitTypes();
    }
    public ObservableList<MapObjectType> getUnitTypes () {
        return model.getUnitTypes();
    }
    
    @Override
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return model.getImmutableStructureTypes();
	}
	public ObservableList<MapObjectType> getStructureTypes() {
		return model.getStructureTypes();
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

	@Override
	public void setMapObjectTypeToMap(MapObjectType mapType) {
		mapobjecttype = mapType;
	}
	
	@Override
	public MapObjectType getMapObjectTypeToMap() {
		return mapobjecttype;
	}

	@Override
	public Image getCurrDraggedImage() {
		return currDraggedImage;
	}

	@Override
	public void setCurrDraggedImage(Image draggedImage) {
		currDraggedImage = draggedImage;	
	}
    
	@Override
	/**
	 * create custom map object from a property object
	 * Access type via type key
	 */
	public void createCustomMapObject(ObjectProperty p) {
		// TODO Auto-generated method stub
		// Debug:
		
		System.out.println("saving");
		p.printProperty();
		
	}

}
