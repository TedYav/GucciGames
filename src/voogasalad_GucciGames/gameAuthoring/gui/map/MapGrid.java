package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapGrid extends Pane {
	
	private final ImageView myBackground;
	private final DoubleProperty myCellSize;
	private Rectangle myMouseBound;
	
	public MapGrid(DoubleProperty cellSize) {
		myBackground = new ImageView();
		getChildren().setAll(myBackground);
		myBackground.fitWidthProperty().bind(widthProperty());
		myBackground.fitHeightProperty().bind(heightProperty());
		myCellSize = cellSize;
		
		setOnMouseMoved(e -> trackMouseMove(e.getX(),e.getY()));
		setOnDragOver(e -> trackMouseMove(e.getX(),e.getY()));
		setOnMouseExited(e->removeMouseBound());
		setOnDragExited(e->removeMouseBound());
		
		//Experiments
		setOnDragDropped(e->System.out.println("Dropped"));
		setOnDragDetected(e->System.out.println("Detected"));
		setOnDragEntered(e->System.out.println("Entered"));
		setOnDragDone(e->System.out.println("Done"));
		setOnMouseReleased(e->System.out.println("Released"));
		
	}
	
	public void initGrid(int width,int height){
		maxWidthProperty().bind(myCellSize.multiply(width));
		maxHeightProperty().bind(myCellSize.multiply(height));
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				new CellGUI(this, x, y);
			}
		}
	}
	
	private void trackMouseMove(double x,double y) {
		double size = myCellSize.get();
		double xt = x - x % size;
		double yt = y - y % size;
		if (myMouseBound == null) {
			addMouseBound(xt, yt, size);
		} else if (!myMouseBound.contains(x, y)) {
			getChildren().remove(myMouseBound);
			addMouseBound(xt, yt, size);
		}
	}
	
	private void addMouseBound(double x,double y, double size){
		myMouseBound = new Rectangle(x, y, size, size);
		myMouseBound.setFill(Color.TRANSPARENT);
		myMouseBound.setStroke(Color.YELLOW);
		myMouseBound.setStrokeWidth(2);
		getChildren().add(myMouseBound);
	}

	private void removeMouseBound() {
		if (myMouseBound != null) {
			getChildren().remove(myMouseBound);
			myMouseBound = null;
		}
	}
	
	public void setBackground(Image img){
		myBackground.setImage(img);
	}

	public DoubleProperty getCellSize() {
		return myCellSize;
	}

}
