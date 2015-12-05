package voogasalad_GucciGames.gameAuthoring.gui.map.cell;

import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
import voogasalad_GucciGames.gameAuthoring.gui.map.ICellGrid;
import voogasalad_GucciGames.gameAuthoring.model.DisplayMapObject;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class Cell extends StackPane implements ICell {

	private final ICellGrid myGrid;
	private final DoubleProperty mySize;
	private final GridPoint myPos;
	private final CellBottomPane myTileLayer;
	private final MapObjectContainer myMidLayer;
	private final CellTopPane myTopLayer;
	private final AGuiGaeController myController;

	private boolean isSelected = false;
	private PopupMenu myMenu;

	public Cell(ICellGrid map, int x, int y) {
		myController = map.getController();
		myGrid = map;
		mySize = myGrid.getCellSize();
		myPos = new GridPoint(x, y);
		myTileLayer = new CellBottomPane(map.getController());
		myMidLayer = new MapObjectContainer(map.getController());
		myTopLayer = new CellTopPane();
		createBindings();
		getChildren().addAll(myTileLayer, myMidLayer, myTopLayer);
		setOnMouseClicked(e -> mouseClickEvent(e));

	}

	private void createBindings() {
		layoutXProperty().bind(mySize.multiply(myPos.getX()));
		layoutYProperty().bind(mySize.multiply(myPos.getY()));
		maxHeightProperty().bind(mySize);
		maxWidthProperty().bind(mySize);
		minHeightProperty().bind(mySize);
		minWidthProperty().bind(mySize);
		bind(myTileLayer);
		bind(myMidLayer);
		bind(myTopLayer);
	}

	private void mouseClickEvent(MouseEvent e) {
		if (!e.isStillSincePress())
			return;
		if (e.getButton() == MouseButton.PRIMARY) {
			if (myController.getSelectedType() != null)
				add(myController.getSelectedType());
			else {
				if (isSelected())
					deselect();
				else
					select();
			}
		} else if (e.getButton() == MouseButton.SECONDARY) {
			showMenu(e);
		}
		e.consume();
	}

	public void select() {
		if (!isSelected) {
			myTopLayer.addBound();
			myGrid.selectCell(this);
			isSelected = true;
		}
	}

	public void deselect() {
		if (isSelected) {
			myTopLayer.removeBound();
			myGrid.deselectCell(this);
			isSelected = false;
		}
	}

	public boolean isSelected() {
		return isSelected;
	}

	private void showMenu(MouseEvent e) {
		if (myMenu == null)
			myMenu = new PopupMenu(myGrid.getController(), this);
		myMenu.update();
		myMenu.show(this, e.getScreenX(), e.getScreenY());
	}

	public GridPoint getPosition() {
		return myPos;
	}

	@Override
	public boolean add(MapObjectType type) {
		DisplayMapObject obj = myController.addObject(myPos, type);
		if (obj != null) {
			if (type.isTile()) {
				myTileLayer.add(obj);
			} else {
				myMidLayer.add(obj);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(DisplayMapObject obj) {
		if (obj.getType().isTile()) {
			return myTileLayer.remove();
		} else {
			return myMidLayer.remove(obj);
		}
	}

	@Override
	public void clear() {
		myTileLayer.remove();
		myMidLayer.clear();
		deselect();
	}

	@Override
	public void highlight() {
		myTopLayer.addBound();

	}

	@Override
	public void dehighlight() {
		myTopLayer.removeBound();
	}

	private void bind(Pane pane) {
		pane.minWidthProperty().bind(widthProperty());
		pane.maxWidthProperty().bind(widthProperty());
		pane.prefWidthProperty().bind(widthProperty());
		pane.minHeightProperty().bind(heightProperty());
		pane.maxHeightProperty().bind(heightProperty());
		pane.prefHeightProperty().bind(heightProperty());
	}

}
