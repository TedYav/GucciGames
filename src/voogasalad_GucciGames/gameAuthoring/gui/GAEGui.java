package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.GameSettingDialog;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.TileMaker;

public class GAEGui /*extends BorderPane*/{
	
    private IGuiGaeController myController;
   
    
    public GAEGui(IGuiGaeController controller, Stage stage){
        myController = controller;
        Group root = new Group();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        
        Button btn = new Button();
        btn.setText("Open Dialog");
        root.getChildren().add(btn);
        btn.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	//GameSettingDialog dialog = new GameSettingDialog();
                	TileMaker dialog = new TileMaker();
                	dialog.showGameSettingsDialog();
                }
             });
        

    
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
