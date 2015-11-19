package voogasalad_GucciGames.gameAuthoring.gui.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

class Grid extends Pane {

	private final ImageView myBackground;
	private final DoubleProperty myCellSize;
	private final Set<Cell> selectedCells = new HashSet<>();
	private final Map<GridPoint, Cell> myCells = new HashMap<>();
	private Rectangle myMouseBound;
	private ImageView myMouseImg;
	private AGuiGaeController myController;

	public Grid(DoubleProperty cellSize, AGuiGaeController controller) {
		myController = controller;
		myBackground = new ImageView();
		getChildren().setAll(myBackground);
		myBackground.fitWidthProperty().bind(widthProperty());
		myBackground.fitHeightProperty().bind(heightProperty());
		myCellSize = cellSize;

		setOnMouseMoved(e -> trackMouseMove(e.getX(), e.getY()));
		addEventFilter(MouseEvent.DRAG_DETECTED, e -> removeMouseBound());
		setOnDragOver(e -> trackMouseMove(e.getX(), e.getY()));
		setOnMouseExited(e -> removeMouseBound());
		setOnDragExited(e -> removeMouseBound());
		myCellSize.addListener((c, o, n) -> removeMouseBound());
		new GridSelector(this);
	}

	public void initGrid(int width, int height) {
		maxWidthProperty().bind(myCellSize.multiply(width));
		maxHeightProperty().bind(myCellSize.multiply(height));
		minWidthProperty().bind(myCellSize.multiply(width));
		minHeightProperty().bind(myCellSize.multiply(height));
		// Pane pane = new Pane();
		// getChildren().setAll(myBackground, pane);
		// pane.minWidthProperty().bind(widthProperty());
		// pane.maxWidthProperty().bind(widthProperty());
		// pane.minHeightProperty().bind(heightProperty());
		// pane.maxHeightProperty().bind(heightProperty());
		addEventFilter(MouseEvent.MOUSE_CLICKED, e -> placeObjectOnMap(e));

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				new Cell(this, x, y);
			}
		}
	}

	private void trackMouseMove(double x, double y) {
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

	private void addMouseBound(double x, double y, double size) {
		if (myController.getCurrSelectedImage() != null
				&& !myCells.containsKey(new GridPoint((int) (x / size), (int) (y / size)))) {
			myMouseImg = new ImageView(myController.getCurrSelectedImage());
			myMouseImg.setFitWidth(size);
			myMouseImg.setFitHeight(size);
			myMouseImg.setX(x);
			myMouseImg.setY(y);
			myMouseImg.setOpacity(0.4);
			myMouseImg.setMouseTransparent(true);
			getChildren().add(myMouseImg);
		}
		myMouseBound = new Rectangle(x, y, size, size);
		myMouseBound.setFill(Color.TRANSPARENT);
		myMouseBound.setStroke(Color.YELLOW);
		myMouseBound.setStrokeWidth(2);
		myMouseBound.setMouseTransparent(true);
		getChildren().add(myMouseBound);
	}

	private void removeMouseBound() {
		if (myMouseImg != null) {
			getChildren().remove(myMouseImg);
			myMouseImg = null;
		}
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

	public boolean selectCell(Cell cell) {
		return selectedCells.add(cell);
	}

	public boolean deselectCell(Cell cell) {
		return selectedCells.remove(cell);
	}

	public void removeSelectedCells() {
		selectedCells.forEach(cell -> {
			cell.removeFromMap();
		});
		selectedCells.clear();
	}

	private void placeObjectOnMap(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY && myController.getCurrSelectedImage() != null) {
			int x = (int) Math.floor(e.getX() / myCellSize.get());
			int y = (int) Math.floor(e.getY() / myCellSize.get());
			Cell gui = new Cell(this, x, y);
			gui.setImage(myController.getCurrSelectedImage());
			e.consume();
		}
	}

	public Cell getCell(GridPoint pt) {
		return myCells.get(pt);
	}

	public void remove(Cell cell) {
		getChildren().remove(cell.getMapView());
		myCells.remove(cell.getPosition());
	}

	public void add(Cell cell) {
		getChildren().add(cell.getMapView());
		myCells.put(cell.getPosition(), cell);
	}
	
	protected AGuiGaeController getController(){
		return myController;
	}

}
