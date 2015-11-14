package voogasalad_GucciGames.gameAuthoring.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.GaeController;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
	
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());
		new GaeController(stage);
		stage.show();

	}
	
	public static void main(String[] args){
		launch(args);
	
	}

}
