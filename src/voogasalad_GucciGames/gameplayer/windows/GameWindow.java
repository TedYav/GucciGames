package voogasalad_GucciGames.gameplayer.windows;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameController;

public abstract class GameWindow implements GameWindowInterface {

	int myID;
	private ResourceBundle myConfig = PlayerConfig.load("WindowManager");
	private Stage myStage;
	private GameWindowManager myManager;
	protected GameController myController;

	public GameWindow(int id, GameWindowManager manager, GameController controller) {
		myID = id;
		myController = controller;
		myManager = manager;
		myStage = new Stage();
		displayWindow();
	}

	private void displayWindow() {
		Map<String, String> myWindowConfig = parseConfig();
		if (myWindowConfig.get("Width") == null || myWindowConfig.get("Height") == null) {
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			myStage.setX(primaryScreenBounds.getMinX());
			myStage.setY(primaryScreenBounds.getMinY());
			myStage.setWidth(primaryScreenBounds.getWidth());
			myStage.setHeight(primaryScreenBounds.getHeight());
		} else {
			myStage.setWidth(Integer.parseInt(myWindowConfig.get("Width")));
			myStage.setHeight(Integer.parseInt(myWindowConfig.get("Height")));
		}
		myStage.setResizable(Integer.parseInt(myWindowConfig.get("Resizable")) == 1);
		myStage.setTitle(myWindowConfig.get("Title"));
		myStage.show();
	}

	private Map<String, String> parseConfig() {
		return myConfig.keySet().stream().filter((s) -> s.matches("^[\\s\\S]*," + myID + "$")).collect(
				Collectors.toMap((s) -> s.toString().split(",")[0].substring(6), (s) -> myConfig.getString(s)));
	}

	@Override
	public void loadScene(Scene scene) {
		myStage.setScene(scene);
	}

	@Override
	public Stage getStage() {
		return myStage;
	}

	public GameWindowManager getManager() {
		return myManager;
	}

	public abstract void initialize();

	public abstract void refresh();

}
