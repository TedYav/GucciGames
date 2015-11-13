package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.menubar.GAEMenuBar;

public class GAEGui extends BorderPane{
	
    private IGuiGaeController myController;
    
    public GAEGui(IGuiGaeController controller, Stage stage){
        myController = controller;
        //Group root = new Group();
        Scene scene = new Scene(this);
        //stage.setScene(scene);
        stage.setScene(scene);
        
        HBox statusbar = new HBox();
        statusbar.getChildren().add(new GAEMenuBar(controller));
        statusbar.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, new CornerRadii(0), getInsets())));
        setTop(new GAEMenuBar(controller));
        setBottom(statusbar);
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
