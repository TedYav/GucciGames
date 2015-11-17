package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

/** Constructs a scene with a pannable Map background. */
public class GUIMap extends Pane implements IMap {
	private static final int GRID_SIZE = 20;

	private IGuiGaeController myController;
	private Grid myGrid;
	private ScrollPane myGridViewer;

	public GUIMap(IGuiGaeController controller) {
		myController = controller;
		myGridViewer = new ScrollPane();
		DoubleProperty cellSize = new SimpleDoubleProperty(myGridViewer.getViewportBounds().getWidth() / GRID_SIZE);
		myGridViewer.viewportBoundsProperty().addListener((ch, oV, nV) -> cellSize.set(nV.getWidth() / GRID_SIZE));

		myGrid = new Grid(cellSize, myController);
		myGridViewer.setContent(myGrid);
		myGridViewer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		myGridViewer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		myGridViewer.setPannable(true);
		// center the scroll contents.
		myGridViewer.setHvalue(myGridViewer.getHmin() + (myGridViewer.getHmax() - myGridViewer.getHmin()) / 2);
		myGridViewer.setVvalue(myGridViewer.getVmin() + (myGridViewer.getVmax() - myGridViewer.getVmin()) / 2);

		myGridViewer.prefViewportWidthProperty().bind(widthProperty());
		myGridViewer.prefViewportHeightProperty().bind(heightProperty());

		addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.BACK_SPACE || e.getCode() == KeyCode.DELETE)
				myGrid.removeSelectedCells();
		});

		addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.CONTROL)
				myGridViewer.setPannable(false);
		});
		addEventHandler(KeyEvent.KEY_RELEASED, e -> {
			if (e.getCode() == KeyCode.CONTROL)
				myGridViewer.setPannable(true);
		});
		
		addEventFilter(KeyEvent.KEY_PRESSED, e->myGrid.scroll(e));
		getChildren().add(myGridViewer);

		Rectangle rect = new Rectangle(0, 0, 200, 200);
		rect.setFill(Color.BLACK);

		Pane pane = new Pane();
		pane.setMaxSize(200, 200);
		pane.setLayoutX(50);
		pane.setLayoutY(50);
		pane.getChildren().add(rect);

		// getChildren().add(pane);

	}

	public void initGrid(int width, int height) {
		myGrid.initGrid(width, height);
	}

	public void setBackground(Image background) {
		myGrid.setBackground(background);
	}

	@Override
	public void setMapObjectForPosition(CellGUI obj, Point2D pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMapObjectAtPosition(CellGUI obj, Point2D pos) {
		// TODO Auto-generated method stub

	}

	public ScrollPane getPane() {
		return myGridViewer;
	}

}