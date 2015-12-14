package voogasalad_GucciGames.gameplayer;

import javafx.application.Application;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameplayer.windows.GameWindowManager;

public class PlayerMain extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		GameWindowManager windowmanager = new GameWindowManager();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
