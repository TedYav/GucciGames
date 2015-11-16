package voogasalad_GucciGames.gameAuthoring.gui.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;

class Grid extends Pane {

	private final ImageView myBackground;
	private final DoubleProperty myCellSize;
	private final Set<CellGUI> selectedCells = new HashSet<>();
	private final Map<GridPoint, CellGUI> myCells = new HashMap<>();
	private Rectangle myMouseBound;
	private ImageView myMouseImg;
	@SuppressWarnings("unused")
	private GridSelector myGridSelector;
	private IGuiGaeController myController;

	public Grid(DoubleProperty cellSize, IGuiGaeController controller) {
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

	}

	public void initGrid(int width, int height) {
		maxWidthProperty().bind(myCellSize.multiply(width));
		maxHeightProperty().bind(myCellSize.multiply(height));
		Pane pane = new Pane();
		getChildren().setAll(myBackground, pane);
		pane.minWidthProperty().bind(widthProperty());
		pane.maxWidthProperty().bind(widthProperty());
		pane.minHeightProperty().bind(heightProperty());
		pane.maxHeightProperty().bind(heightProperty());
		pane.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> placeObject(e));

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				new CellGUI(this, x, y);
			}
		}
		myGridSelector = new GridSelector(this);
	}

	public void scroll(KeyEvent e) {
		if (e.getCode() == KeyCode.M)
			myCellSize.set(myCellSize.get() * 1.1);
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
		if (myController.getCurrDraggedImage() != null
				&& !myCells.containsKey(new GridPoint((int) (x / size), (int) (y / size)))) {
			myMouseImg = new ImageView(myController.getCurrDraggedImage());
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

	private void placeObject(MouseEvent e) {
		if (myController.getCurrDraggedImage() == null)
			return;
		int x = (int) Math.floor(e.getX() / myCellSize.get());
		int y = (int) Math.floor(e.getY() / myCellSize.get());

		CellGUI gui = new CellGUI(this, x, y);
		gui.setImage(myController.getCurrDraggedImage());
	}

	public CellGUI getCell(GridPoint pt) {
		return myCells.get(pt);
	}

	public void remove(CellGUI cell) {
		getChildren().remove(cell.getMapView());
		myCells.remove(cell.getPosition());
	}

	public void add(CellGUI cell) {
		getChildren().add(cell.getMapView());
		myCells.put(cell.getPosition(), cell);
	}

}
