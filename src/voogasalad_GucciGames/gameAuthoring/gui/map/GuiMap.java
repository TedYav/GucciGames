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
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

/** Constructs a scene with a pannable Map background. */
public class GuiMap extends Pane implements IGuiMap{
	private IntegerProperty myGridSize = new SimpleIntegerProperty(20);
	private AGuiGaeController myController;
	private Grid myGrid;
	private ScrollPane myGridViewer;

	public GuiMap(AGuiGaeController controller) {
		myController = controller;
		createGridViewer();
		addEventHandlers();

	}
	
	private void createGridViewer(){
		myGridViewer = new ScrollPane();
		DoubleProperty cellSize = new SimpleDoubleProperty();
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
		getChildren().add(myGridViewer);
	}
	
	private void addEventHandlers(){
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
		addEventHandler(KeyEvent.KEY_PRESSED, e->keyZoom(e));
		addEventHandler(ZoomEvent.ANY, e->gestureZoom(e));
	}
	
	private void gestureZoom(ZoomEvent e) {
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
	
	private void keyZoom(KeyEvent e) {
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
	
	@Override
	public void initGrid(int width, int height) {
		myGrid.initGrid(width, height);
	}
	
	@Override
	public void setBackground(Image background) {
		myGrid.setBackground(background);
	}

}