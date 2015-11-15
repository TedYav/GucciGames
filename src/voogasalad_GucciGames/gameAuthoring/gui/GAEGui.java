package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
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
	private GUIMap myMap;

	public GAEGui(IGuiGaeController controller, Stage stage) {
		myController = controller;
		stage.setScene(new Scene(this));

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setWidth(screenBounds.getWidth());
		stage.setHeight(screenBounds.getHeight());
		stage.show();
		initLayout(stage);
		initializeMap(100, 100);
	}

	private TabPane rightPane(Stage stage) {
		TabPane rightTabPane = new TabPane();
    	TileTab tileTab = new TileTab(myController);
    	UnitTab unitTab = new UnitTab(myController);
    	StructureTab strucTab = new StructureTab(myController);
    	
    	setSize(tileTab,stage);
    	setSize(unitTab,stage);
    	setSize(strucTab,stage);
    	
    	rightTabPane.getTabs().addAll(tileTab, unitTab, strucTab);
    	return rightTabPane;
	}
	
	private void initLayout(Stage stage){
		GAEMenuBar menuBar = null;
		try {
			menuBar = new GAEMenuBar(myController);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTop(menuBar);
		StatusBar statusBar = new StatusBar(myController);
		setBottom(statusBar);
		setRight(rightPane(stage));
		
		myMap = new GUIMap(myController);
		myMap.setOnMouseMoved(e->statusBar.update(e));
		setCenter(myMap);

		myMap.setBackground(new Image("http://www.narniaweb.com/wp-content/uploads/2009/08/NarniaMap.jpg"));
	}

	public void initializeMap(int width, int height) {
		myMap.initGrid(width, height);
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
	
	private void setSize(Tab tab, Stage stage){
		VBox currBox = (VBox) tab.getContent();
    	currBox.setMinWidth(stage.getWidth()/4);
	}
}
