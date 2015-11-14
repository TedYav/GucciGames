package voogasalad_GucciGames.gameAuthoring.gui;

import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.StructureTab;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.TileTab;
import voogasalad_GucciGames.gameAuthoring.gui.sidebar.UnitTab;
import voogasalad_GucciGames.gameAuthoring.gui.menubar.GAEMenuBar;

public class GAEGui extends BorderPane {

	private IGuiGaeController myController;

	public GAEGui(IGuiGaeController controller, Stage stage) {
		myController = controller;
		stage.setScene(new Scene(this));

		addRightPane(stage);
		try {
			HBox statusbar = new HBox();
			statusbar.getChildren().add(new GAEMenuBar(controller));
			statusbar.setBackground(
					new Background(new BackgroundFill(Color.AQUAMARINE, new CornerRadii(0), getInsets())));
			setTop(new GAEMenuBar(controller));
			setBottom(statusbar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addRightPane(Stage stage) {
		TabPane rightTabPane = new TabPane();
    	TileTab tileTab = new TileTab();
    	UnitTab unitTab = new UnitTab();
    	StructureTab strucTab = new StructureTab();
    	
    	setSize(tileTab,stage);
    	setSize(unitTab,stage);
    	setSize(strucTab,stage);
    	
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
	
	private void setSize(Tab tab, Stage stage){
		VBox currBox = (VBox) tab.getContent();
    	currBox.setMinWidth(stage.getWidth()/4);
	}
}
