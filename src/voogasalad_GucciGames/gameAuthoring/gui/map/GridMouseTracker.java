package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

class GridMouseTracker {
	
	private Rectangle myMouseBound = null;
	private ImageView myMouseImg = null;
	private final DoubleProperty myCellSize;
	private final Grid myGrid;
	private final IGuiGaeController myController;
	
	public GridMouseTracker(Grid grid) {
		myGrid = grid;
		myCellSize = grid.getCellSize();
		myController = grid.getController();
		grid.setOnMouseMoved(e -> mouseMoved(e.getX(), e.getY()));
		grid.addEventFilter(MouseEvent.DRAG_DETECTED, e -> removeMouseBound());
		grid.setOnDragOver(e -> onDragOver(e));
		grid.setOnDragDropped(e->onDragDropped(e));
		grid.setOnMouseExited(e -> removeMouseBound());
		grid.setOnDragExited(e -> removeMouseBound());
		myCellSize.addListener((c, o, n) -> removeMouseBound());
	}
	
	private void mouseMoved(double x, double y) {
		double size = myCellSize.get();
		double xt = x - x % size;
		double yt = y - y % size;
		if (myMouseBound == null) {
			addMouseBound(xt, yt, size);
		} else if (!myMouseBound.contains(x, y)) {
			removeMouseBound();
			addMouseBound(xt, yt, size);
		}
	}
	
	private void onDragOver(DragEvent e){
		if(e.getDragboard().hasImage())
			e.acceptTransferModes(TransferMode.ANY);
	}
	
	private void onDragDropped(DragEvent e) {
        if (myController.getDragType()!=null) {
            myGrid.addTypeToSelectedCells(myController.getDragType());
            myController.setDragType(null);
            e.setDropCompleted(true);
            e.consume();
        }
	}

	private void addMouseBound(double x, double y, double size) {
		if (myController.getSelectedType() != null) {
			myMouseImg = myController.getMapObjectImage(myController.getSelectedType());
			myMouseImg.setFitWidth(size);
			myMouseImg.setFitHeight(size);
			myMouseImg.setX(x);
			myMouseImg.setY(y);
			myMouseImg.setOpacity(0.4);
			myMouseImg.setMouseTransparent(true);
			myGrid.getChildren().add(myMouseImg);
		}
		myMouseBound = new Rectangle(x, y, size, size);
		myMouseBound.setFill(Color.TRANSPARENT);
		myMouseBound.setStroke(Color.YELLOW);
		myMouseBound.setStrokeWidth(2);
		myMouseBound.setMouseTransparent(true);
		myGrid.getChildren().add(myMouseBound);
	}

	private void removeMouseBound() {
		if (myMouseImg != null) {
			myGrid.getChildren().remove(myMouseImg);
			myMouseImg = null;
		}
		if (myMouseBound != null) {
			myGrid.getChildren().remove(myMouseBound);
			myMouseBound = null;
		}
	}
}
