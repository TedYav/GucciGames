package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameData.wrapper.GamePlayerSave;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;

public class MainMenuOverlay extends WindowComponent {

	private StackPane myParent;
	private Shape myBackground;
	private MenuScreen myMenu;
	private String myTitle;

	private ResourceBundle myConfig = PlayerConfig.load("components.MainMenuOverlay");
	
	public MainMenuOverlay(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		myTitle = myConfig.getString("MenuTitle");
		myMenu = new MenuScreen(getGameScene(), getController(), buildOptionMap(), myTitle);
		myParent = new StackPane();
		myBackground = new Rectangle(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
		myBackground.getStyleClass().add("overlay-background");
		myParent.getChildren().addAll(myBackground, myMenu.getParent());
		setParent(myParent);
	}

	private Map<String, MenuAction> buildOptionMap() {
		Map<String, MenuAction> options = new LinkedHashMap<>();
		options.put("New Game", () -> getGameScene().getManager().loadScene("MainMenuScene"));
		options.put("Load Game", () -> getGameScene().getManager().loadScene("LoadGameScene"));
		options.put("Save Game", () -> getGameScene().getManager().getLoader().saveGame());
		options.put("View High Scores", () -> getGameScene().getManager().loadScene("HighScoresScene"));
		options.put("Reload", () -> { getGameScene().getManager().getLoader().loadGame(getGameScene().getManager().getController().getGame().getGameName()); 
		getGameScene().getManager().loadScene("GameSplashScene"); });
		options.put("Quit", () -> getGameScene().getManager().loadScene("MainMenuScene"));
		return options;
	}
	
}
