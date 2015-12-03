package voogasalad_GucciGames.gameplayer.windows.mainwindow.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.helpers.FontManager;

public class MenuScreen extends WindowComponent {

	// TODO: factor parent into superclass
	private StackPane myPane;
	private Pane myBackground;
	private Pane myMenu;
	
	private Map<String, Consumer<String>> myOptions;
	
	private ResourceBundle myConfig = PlayerConfig.load("components.MenuScreen");
	
	public MenuScreen(GameScene scene, GameControllerInterface controller, Map<String, Consumer<String>> options) {
		super(scene, controller);
		myOptions = options;
		initializePanes();
		drawMenu();
	}

	private void drawMenu() {
		Text test = new Text("testing");
		//test.setFont(f);
		test.getStyleClass().add("menufont");
		myPane.getChildren().add(test);
	}

	private void initializePanes() {
		myPane = new StackPane();
		myBackground = new Pane();
		myMenu = new Pane();
		myPane.getChildren().addAll(myBackground, myMenu);
		setParent(myPane);
	}


}
