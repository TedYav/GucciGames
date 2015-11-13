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
import voogasalad_GucciGames.gameAuthoring.gui.menubar.GAEMenuBar;

public class GAEGui extends BorderPane {

	private IGuiGaeController myController;

	public GAEGui(IGuiGaeController controller, Stage stage) {
		myController = controller;
		stage.setScene(new Scene(this));

		addRightPane();
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
