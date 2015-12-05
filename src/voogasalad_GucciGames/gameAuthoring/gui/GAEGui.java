package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.SideBar;
import voogasalad_GucciGames.gameAuthoring.gui.statusbar.StatusBar;
import voogasalad_GucciGames.gameAuthoring.gui.levels.LevelTab;
import voogasalad_GucciGames.gameAuthoring.gui.levels.LevelTabPane;
import voogasalad_GucciGames.gameAuthoring.gui.map.GuiMap;
import voogasalad_GucciGames.gameAuthoring.gui.menubar.GAEMenuBar;

/**
 * TODO:
 * 1. Select animation
 * 2. Add components in backend
 * 3. Select size of the map
 * @author Mike Ma (ym67)
 *
 */
public class GAEGui extends BorderPane {

	private AGuiGaeController myController;
	private LevelTabPane myLevelTabPane;
//	private GuiMap myMap;
	// private ISaveCustomObj saveCustomObj;

	public GAEGui(AGuiGaeController controller, Stage stage) {
		myController = controller;
		stage.setScene(new Scene(this));

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setWidth(screenBounds.getWidth());
		stage.setHeight(screenBounds.getHeight());
		initLayout(stage);
		//initializeMap(5, 5);
		stage.show();
	}

	private void initLayout(Stage stage) {

		// Add Menu Bar
		try {
			GAEMenuBar menuBar = new GAEMenuBar(myController);
			setTop(menuBar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Add Status Bar
		StatusBar statusBar = new StatusBar(myController);
		setBottom(statusBar);

		// Add Side Bar
		TabPane sideBar = (new SideBar(myController)).getPane();
		sideBar.maxWidthProperty().bind(widthProperty().divide(4));
		sideBar.minWidthProperty().bind(widthProperty().divide(4));
		setRight(sideBar);

		// Add Map
		myLevelTabPane = new LevelTabPane(myController, statusBar);
		setCenter(myLevelTabPane);
//		myMap = new GuiMap(myController);
//		myMap.setOnMouseMoved(e -> statusBar.update(e));
//		setCenter(myMap);
//		myMap.setBackground(new Image("http://www.narniaweb.com/wp-content/uploads/2009/08/NarniaMap.jpg"));
	}

	public void initializeMap(int width, int height) {
		LevelTab levelTab = myLevelTabPane.getCurrTab();
		myLevelTabPane.initializeMap(width, height, levelTab);
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
	
	public LevelTabPane getLevelTabPane(){
		return myLevelTabPane;
	}

	public AGuiGaeController getController() {
		return myController;
	}

}
