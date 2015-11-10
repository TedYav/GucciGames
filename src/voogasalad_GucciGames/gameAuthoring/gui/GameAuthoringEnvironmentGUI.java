package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;
import javafx.collections.ListChangeListener;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

public class GameAuthoringEnvironmentGUI implements ListChangeListener{
    private IGuiGaeController myController;
    public GameAuthoringEnvironmentGUI(IGuiGaeController controller){
        myController = controller;
    }

    public void initializeMap(int width, int height/*, Grid g*/){

    }
    
    @Override
    public void onChanged (Change arg0) {
        // TODO Auto-generated method stub

    }
    
    /**
     * Mock methods for use case purposes, can delete if obsolete.
     */
    
    /**
     * 
     * For when user creates a custom MapObjectType.
     * @return the Map that specifies the MapObjectType properties
     */
    public Map<String,String> getMapForCustomTile() {
        return null;
    }
    public IGuiGaeController getController() {
        return myController;
    }
}
