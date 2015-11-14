package voogasalad_GucciGames.gameAuthoring.main;

import javafx.application.Application;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.GaeController;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setResizable(false);
		new GaeController(stage);

	}
	
	public static void main(String[] args){
		launch(args);
	}

}
