package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.TileTab;
import voogasalad_GucciGames.gameAuthoring.gui.statusbar.StatusBar;
import voogasalad_GucciGames.gameAuthoring.gui.map.GUIMap;
import voogasalad_GucciGames.gameAuthoring.gui.map.IGUIMap;
import voogasalad_GucciGames.gameAuthoring.gui.menubar.GAEMenuBar;

public class GAEGui extends BorderPane {

	private IGuiGaeController myController;

	public GAEGui(IGuiGaeController controller, Stage stage) {
		myController = controller;
		stage.setScene(new Scene(this));
		
		addRightPane();
		GAEMenuBar menuBar = null;
		try {
			menuBar = new GAEMenuBar(controller);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTop(menuBar);
		StatusBar statusBar = new StatusBar(controller);
		setBottom(statusBar);
		setOnMouseMoved(e->statusBar.update(e));
		GUIMap map = new GUIMap(myController);
		
		setCenter(map);
	}

	private void addRightPane() {
		TabPane rightTabPane = new TabPane();
		TileTab tileTab = new TileTab();
		rightTabPane.getTabs().add(tileTab);
		setRight(rightTabPane);
	}

	public void initializeMap(int width, int height/* , Grid g */) {

	}

	/**
	 * Mock methods for use case purposes, can delete if obsolete.
	 */

	/**
	 * 
	 * For when user creates a custom MapObjectType.
	 * 
	 * @return the Map that specifies the MapObjectType properties
	 */
	public Map<String, String> getMapForCustomTile() {
		return null;
	}

	public IGuiGaeController getController() {
		return myController;
	}
}
