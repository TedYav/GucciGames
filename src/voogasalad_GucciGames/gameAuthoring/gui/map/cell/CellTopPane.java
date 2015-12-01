package voogasalad_GucciGames.gameAuthoring.gui.map.cell;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class CellTopPane extends Pane {
	
	private Rectangle myBoundBox;
	
	CellTopPane() {
		myBoundBox = new Rectangle();
		myBoundBox.widthProperty().bind(widthProperty());
		myBoundBox.heightProperty().bind(heightProperty());
		myBoundBox.setStroke(Color.YELLOW);
		myBoundBox.setFill(Color.rgb(0, 255, 255, 0.2));
		myBoundBox.setStrokeWidth(2);
		myBoundBox.setMouseTransparent(true);
		myBoundBox.setVisible(false);
		getChildren().add(myBoundBox);
	}

	
	public void addBound() {
		myBoundBox.setVisible(true);
	}
	
	public void removeBound() {
		myBoundBox.setVisible(false);
	}
}
