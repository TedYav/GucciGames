package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.NewLevelDialog;
import voogasalad_GucciGames.gameAuthoring.gui.levels.LevelTabPane;
import voogasalad_GucciGames.gameAuthoring.gui.menubar.GAEMenuBar;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.SideBar;
import voogasalad_GucciGames.gameAuthoring.gui.statusbar.StatusBar;
import voogasalad_GucciGames.gameAuthoring.gui.util.GAEPreloader;
import voogasalad_GucciGames.gameAuthoring.gui.util.ShortcutAdder;
import voogasalad_GucciGames.gameAuthoring.gui.util.WelcomeScreen;
import voogasalad_GucciGames.gameAuthoring.guiexceptions.ErrorDialog;

/**
 * TODO: 1. Select animation 2. Add components in backend
 * 
 * @author Mike Ma (ym67)
 *
 */
public class GAEGui extends BorderPane {

	private final AGuiGaeController myController;
	private LevelTabPane myLevelTabPane;
	private StatusBar myStatusBar;
	private GAEMenuBar myMenuBar;
	private final WelcomeScreen myWelcomeScreen = new WelcomeScreen();

	public GAEGui(AGuiGaeController controller, Stage stage) {
		myController = controller;
		GAEPreloader preloader = new GAEPreloader();
		try {
			preloader.start(stage, () -> init());
		} catch (Exception e) {
			controller.throwException(e);
		}
	}

	private void init() {
		myController.initModel();
		// throwException(new IllegalAccessException("Today's a nice day to have
		// a nice day"));
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene(this));
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setWidth(screenBounds.getWidth());
		stage.setHeight(screenBounds.getHeight());

		// Add Menu Bar
		myMenuBar = new GAEMenuBar(myController);
		setTop(myMenuBar);
		// Add Status Bar
		myStatusBar = new StatusBar(myController);
		setBottom(myStatusBar);
		setCenter(myWelcomeScreen);
		stage.show();
		new ShortcutAdder(myController, this);
	}

	private void initLayout() {
		// Add Side Bar
		getChildren().clear();
		setTop(myMenuBar);
		setBottom(myStatusBar);
		TabPane sideBar = (new SideBar(myController)).getPane();
		sideBar.maxWidthProperty().bind(widthProperty().divide(4));
		sideBar.minWidthProperty().bind(widthProperty().divide(4));
		setRight(sideBar);

		// Add Map
		myLevelTabPane = new LevelTabPane(myController);
		setCenter(myLevelTabPane);
		myLevelTabPane.setOnMouseMoved(e -> myStatusBar.update(e));
	}

	public void initGame() {
		initLayout();
		Dialog<Map<String, String>> dialog = new NewLevelDialog(myController);
		dialog.showAndWait().ifPresent(map -> {
			myController.getLevelTabPane().createNewTab(map.get("name"), Integer.parseInt(map.get("width")),
					Integer.parseInt(map.get("height")));
		});
	}

	/**
	 * 
	 * For when user creates a custom MapObjectType.
	 * 
	 * @return the Map that specifies the MapObjectType properties
	 */
	public Map<String, String> getMapForCustomTile() {
		return null;
	}

	public LevelTabPane getLevelTabPane() {
		return myLevelTabPane;
	}

	public AGuiGaeController getController() {
		return myController;
	}

	public void throwException(Exception e) {
		new ErrorDialog(e);
	}

}
