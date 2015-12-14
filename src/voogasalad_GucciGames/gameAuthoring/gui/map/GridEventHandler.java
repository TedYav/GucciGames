// This entire file is part of my masterpiece.
// Mike Ma (ym67)
package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.IntegerProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ZoomEvent;

public class GridEventHandler {
	private final GuiMap myGuiMap;
	private final GridViewer myViewer;
	private final Grid myGrid;
	
	public GridEventHandler(GuiMap guiMap, GridViewer viewer) {
		myGuiMap = guiMap;
		myViewer = viewer;
		myGrid = myViewer.getGrid();
		addEventHandlers();
	}
	
	private void addEventHandlers() {
		myGuiMap.addEventHandler(KeyEvent.KEY_PRESSED, e->onKeyPressed(e));
		myGuiMap.addEventHandler(KeyEvent.KEY_RELEASED, e->onKeyReleased(e));
		myGuiMap.addEventHandler(ZoomEvent.ANY, e -> gestureZoom(e));
	}
	
	private void onKeyPressed(KeyEvent e){
		switch (e.getCode()) {
		case BACK_SPACE:
			myGrid.removeSelectedCells();
			e.consume();
			break;
		case DELETE:
			myGrid.removeSelectedCells();
			e.consume();
			break;
		case CONTROL:
			myViewer.setPannable(false);
			break;
		case MINUS:
			if (myViewer.getGridSize().get() < 50) {
				myViewer.getGridSize().set(myViewer.getGridSize().get() + 1);
			}
			e.consume();
			break;
		case EQUALS:
			if (myViewer.getGridSize().get() > 10) {
				myViewer.getGridSize().set(myViewer.getGridSize().get() - 1);
			}
			e.consume();
			break;
		default:
			break;
		}
	}
	private void onKeyReleased(KeyEvent e){
		switch (e.getCode()) {
		case A:
			if(e.isControlDown()){
				myGrid.selectAll();
				e.consume();
			}
			break;
		case CONTROL:
			myViewer.setPannable(true);
			break;
		default:
			break;
		}
	}

	private void gestureZoom(ZoomEvent e) {
		IntegerProperty gridSize = myViewer.getGridSize();
		if (e.getZoomFactor() < 1) {
			if (gridSize.get() < 50) {
				gridSize.set(gridSize.get() + 1);
			}
		} else {
			if (gridSize.get() > 10) {
				gridSize.set(gridSize.get() - 1);
			}
		}
		e.consume();

	}
}
