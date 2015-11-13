package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.TileTab;

public class GAEGui extends BorderPane{
	
    private IGuiGaeController myController;
    
    public GAEGui(IGuiGaeController controller, Stage stage){
        myController = controller;
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
      
        addRightPane();
        
        root.getChildren().addAll(this);
    }

    private void addRightPane() {
    	TabPane rightTabPane = new TabPane();
    	TileTab tileTab = new TileTab();
    	rightTabPane.getTabs().add(tileTab);
    	setRight(rightTabPane);
	}

	public void initializeMap(int width, int height/*, Grid g*/){

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
