package voogasalad_GucciGames;

import javafx.application.Application;
import javafx.stage.Stage;

public class GucciGames extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Launcher launcher = new Launcher(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
