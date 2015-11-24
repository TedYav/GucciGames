package voogasalad_GucciGames.gameAuthoring.gui.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

class Grid extends Pane {

	private final ImageView myBackground;
	private final DoubleProperty myCellSize;
	private final Set<Cell> selectedCells = new HashSet<>();
	private final Map<GridPoint, Set<Cell>> myCells = new HashMap<>();
	private Rectangle myMouseBound;
	private ImageView myMouseImg;
	private AGuiGaeController myController;

	private List<MapObject> myMapObjects;

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

		myMapObjects = controller.getMapObjects();
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

		// for (int x = 0; x < width; x++) {
		// for (int y = 0; y < height; y++) {
		// new Cell(this, x, y);
		// //TODO: update the MapObjects
		// }
		// }
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
			
			MapObjectType mapType = myController.getMapObjectTypeToMap();
			double width = mapType.getWidth();
			double height = mapType.getHeight();
			
			double myX1 = mapType.getX()*width;
			double myY1 = mapType.getY()*height;
			
			Rectangle2D rect = new Rectangle2D(myX1, myY1, width, height);
			
			myMouseImg = new ImageView(myController.getCurrSelectedImage());
			myMouseImg.setViewport(rect);
			
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
			if (myController.getMapObjectTypeToMap().isTile()) {
				Set<Cell> set = getCell(new GridPoint(x, y));
				if (set != null) {
					for (Cell c : set) {
						if (c.getObject().getObjectType().isTile())
							return;

					}
				}

			}
			Cell gui = new Cell(this, x, y);
			gui.setImage(myController.getCurrSelectedImage(), myController.getMapObjectTypeToMap());
			// TODO: finish up MapObject and fix Cell
			// getMapObjectListPosAtPoint(myMapObjects, );
			e.consume();
		}
	}

	public Set<Cell> getCell(GridPoint pt) {
		return myCells.get(pt);
	}

	public void remove(Cell cell) {
		myController.deleteComponent(cell.getObject());
		getChildren().remove(cell.getMapView());
		myCells.remove(cell.getPosition());
	}

	public void add(Cell cell) {
		MapObject obj = myController.addObject(cell.getPosition(), myController.getMapObjectTypeToMap());
		if (obj != null) {
			getChildren().add(cell.getMapView());
			if (!myCells.containsKey(cell.getPosition()))
				myCells.put(cell.getPosition(), new HashSet<>());
			myCells.get(cell.getPosition()).add(cell);
			cell.setObject(obj);
		}
	}

	public AGuiGaeController getController() {
		return myController;
	}

}
