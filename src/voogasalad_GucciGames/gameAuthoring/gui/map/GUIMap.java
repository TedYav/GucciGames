package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

/** Constructs a scene with a pannable Map background. */
public class GUIMap extends Pane {
	private IntegerProperty myGridSize = new SimpleIntegerProperty(20);

	private AGuiGaeController myController;
	private Grid myGrid;
	private ScrollPane myGridViewer;

	public GUIMap(AGuiGaeController controller) {
		myController = controller;
		myGridViewer = new ScrollPane();
		DoubleProperty cellSize = new SimpleDoubleProperty();
		//DoubleProperty cellSize = new SimpleDoubleProperty(myGridViewer.getViewportBounds().getWidth() / myGridSize.get());
		myGridViewer.viewportBoundsProperty().addListener((ch, oV, nV) -> cellSize.set(nV.getWidth() / myGridSize.get()));
		myGridSize.addListener((ch, oV, nV) -> cellSize.set(myGridViewer.viewportBoundsProperty().get().getWidth() / myGridSize.get()));
		
		myGrid = new Grid(cellSize, myController);
		myGridViewer.setContent(myGrid);
		myGridViewer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		myGridViewer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		//myGridViewer.vmaxProperty().bind(myGridViewer.widthProperty());
		//myGridViewer.hmaxProperty().bind(myGridViewer.heightProperty());
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
		addEventHandler(KeyEvent.KEY_PRESSED, e->scroll(e));
		addEventHandler(ZoomEvent.ANY, e->scroll(e));
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
	
	private void scroll(ZoomEvent e) {
		if (e.getZoomFactor()<1){
			if(myGridSize.get()<50){
				myGridSize.set(myGridSize.get()+1);
			}
		}
		else{
			if(myGridSize.get()>10){
				myGridSize.set(myGridSize.get()-1);
			}	
		}
		e.consume();
			
	}
	
	private void scroll(KeyEvent e) {
		if (e.getCode() == KeyCode.MINUS){
			if(myGridSize.get()<50){
				myGridSize.set(myGridSize.get()+1);
			}
			e.consume();
		}
		else if(e.getCode()==KeyCode.EQUALS){
			if(myGridSize.get()>10){
				myGridSize.set(myGridSize.get()-1);
			}	
			e.consume();
		}
			
	}

	public void initGrid(int width, int height) {
		myGrid.initGrid(width, height);
	}

	public void setBackground(Image background) {
		myGrid.setBackground(background);
	}

	public ScrollPane getPane() {
		return myGridViewer;
	}

}