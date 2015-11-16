package voogasalad_GucciGames.gameAuthoring.gui.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Grid extends Pane {

	private final ImageView myBackground;
	private final DoubleProperty myCellSize;
	private final Set<CellGUI> selectedCells = new HashSet<>();
	private final Map<GridPoint,CellGUI> myCells = new HashMap<>();
	private Rectangle myMouseBound;
	private GridSelector myGridSelector;

	public Grid(DoubleProperty cellSize) {
		myBackground = new ImageView();
		getChildren().setAll(myBackground);
		myBackground.fitWidthProperty().bind(widthProperty());
		myBackground.fitHeightProperty().bind(heightProperty());
		myCellSize = cellSize;

		setOnMouseMoved(e -> trackMouseMove(e.getX(), e.getY()));
		addEventHandler(MouseEvent.DRAG_DETECTED, e->removeMouseBound());
		setOnDragOver(e -> trackMouseMove(e.getX(), e.getY()));
		setOnMouseExited(e -> removeMouseBound());
		setOnDragExited(e -> removeMouseBound());
		
	}

	public void initGrid(int width, int height) {
		maxWidthProperty().bind(myCellSize.multiply(width));
		maxHeightProperty().bind(myCellSize.multiply(height));
		Pane pane = new Pane();
		getChildren().setAll(myBackground,pane);
		pane.minWidthProperty().bind(widthProperty());
		pane.maxWidthProperty().bind(widthProperty());
		pane.minHeightProperty().bind(heightProperty());
		pane.maxHeightProperty().bind(heightProperty());
		pane.setOnMouseClicked(e->placeObject(e));
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				myCells.put(new GridPoint(x, y), new CellGUI(this, x, y));
			}
		}
		myGridSelector = new GridSelector(this);
	}

	private void trackMouseMove(double x, double y) {
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

	private void addMouseBound(double x, double y, double size) {
		myMouseBound = new Rectangle(x, y, size, size);
		myMouseBound.setFill(Color.TRANSPARENT);
		myMouseBound.setStroke(Color.YELLOW);
		myMouseBound.setStrokeWidth(2);
		myMouseBound.setMouseTransparent(true);
		getChildren().add(myMouseBound);
	}

	private void removeMouseBound() {
		if (myMouseBound != null) {
			getChildren().remove(myMouseBound);
			myMouseBound = null;
		}
	}

	public void setBackground(Image img) {
		myBackground.setImage(img);
	}

	public DoubleProperty getCellSize() {
		return myCellSize;
	}

	public boolean selectCell(CellGUI cell) {
		return selectedCells.add(cell);
	}
	
	public boolean deselectCell(CellGUI cell) {
		return selectedCells.remove(cell);
	}

	public void removeSelectedCells() {
		selectedCells.forEach(cell -> {
			cell.removeFromMap();
		});
		selectedCells.clear();
	}
	
	private void placeObject(MouseEvent e){
		int x = (int)Math.floor(e.getX()/myCellSize.get());
		int y = (int)Math.floor(e.getY()/myCellSize.get());
		new CellGUI(this, x, y);
	}
	
	private void fetchDraggedContent(DragEvent e){
		System.out.println(e.getX()+" "+e.getY());
		e.acceptTransferModes(TransferMode.ANY);
		int x = (int)Math.floor(e.getX()/myCellSize.get());
		int y = (int)Math.floor(e.getY()/myCellSize.get());
		//CellGUI cell = new CellGUI(this, x, y);
		System.out.println("New Cell");
		//cell.setImage(e.getDragboard().getImage());
	}
	
	public CellGUI getCell(GridPoint pt){
		return myCells.get(pt);
	}
	
	public CellGUI remove(GridPoint pt){
		return myCells.remove(pt);
	}

}
