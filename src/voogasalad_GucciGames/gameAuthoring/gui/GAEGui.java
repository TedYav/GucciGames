package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.StructureTab;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.TileTab;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.UnitTab;
import voogasalad_GucciGames.gameAuthoring.gui.statusbar.StatusBar;
import voogasalad_GucciGames.gameAuthoring.gui.map.GUIMap;
import voogasalad_GucciGames.gameAuthoring.gui.menubar.GAEMenuBar;

public class GAEGui extends BorderPane {

	private IGuiGaeController myController;

	public GAEGui(IGuiGaeController controller, Stage stage) {
		myController = controller;
		stage.setScene(new Scene(this));

		addRightPane(stage);
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
		stage.show();
		map.initGrid();
		map.setBackground(new Image("http://www.narniaweb.com/wp-content/uploads/2009/08/NarniaMap.jpg"));
	}

	private void addRightPane(Stage stage) {
		TabPane rightTabPane = new TabPane();
    	TileTab tileTab = new TileTab(stage);
    	UnitTab unitTab = new UnitTab(stage);
    	StructureTab strucTab = new StructureTab(stage);
    	rightTabPane.getTabs().addAll(tileTab, unitTab, strucTab);
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
