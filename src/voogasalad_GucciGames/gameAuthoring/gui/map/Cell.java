package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {
	private static Image myImage = new Image(
			Cell.class.getClassLoader().getResourceAsStream("voogasalad_GucciGames/graphics/water.png"));
	private ImageView myMapView;
	private ImageView myMiniView;
	private Rectangle myBoundBox;
	private Grid myMap;
	private DoubleProperty mySize;
	private ContextMenu myMenu;
	private boolean isSelected = false;
	private GridPoint myPos;

	public Cell(Grid map, int x, int y) {
		myMap = map;
		myMapView = new ImageView(myImage);
		mySize = myMap.getCellSize();
		myMapView.fitWidthProperty().bind(mySize);
		myMapView.fitHeightProperty().bind(mySize);
		myMapView.xProperty().bind(mySize.multiply(x));
		myMapView.yProperty().bind(mySize.multiply(y));
		myMapView.setOnMouseClicked(e -> mouseClickEvent(e));
		createMenu();
		myPos = new GridPoint(x, y);
		myMap.add(this);
	}

	public void setImage(Image img) {
		myMapView.setImage(img);
	}

	private void createMenu() {
		MenuItem item1 = new MenuItem("Edit");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Edit");
			}
		});
		MenuItem item2 = new MenuItem("Remove");
		item2.setOnAction(e -> removeFromMap());

		Menu item3 = new Menu("Owner");
		ToggleGroup group = new ToggleGroup();
		RadioMenuItem on = new RadioMenuItem("Player1");
		on.setUserData(1);
		on.setToggleGroup(group);

		RadioMenuItem off = new RadioMenuItem("Player2");
		off.setUserData(2);
		off.setToggleGroup(group);

		on.setSelected(true);
		item3.getItems().addAll(on, off);
		group.selectedToggleProperty().addListener((ob, oldV, newV) -> {
			if (group.getSelectedToggle() != null) {
				// TODO
				// myGui.myCellType = (String)newV.getUserData();
				// myGui.reset();
			}
		});

		myMenu = new ContextMenu(item1, item2, item3);

	}

	private void mouseClickEvent(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			if (isSelected())
				deselect();
			else
				select();
		} else if (e.getButton() == MouseButton.SECONDARY) {
			showMenu(e);
		}
	}

	public void select() {
		if (!isSelected) {
			addBound();
			myMap.selectCell(this);
			isSelected = true;
		}
	}

	public void addBound() {
		if (myBoundBox == null) {
			//myBoundBox = new Rectangle(myMapView.getX(), myMapView.getY(), mySize.get(), mySize.get());
			myBoundBox = new Rectangle();
			myBoundBox.widthProperty().bind(mySize);
			myBoundBox.heightProperty().bind(mySize);
			myBoundBox.xProperty().bind(myMapView.xProperty());
			myBoundBox.yProperty().bind(myMapView.yProperty());
			myBoundBox.setStroke(Color.YELLOW);
			myBoundBox.setFill(Color.rgb(0, 255, 255, 0.2));
			myBoundBox.setStrokeWidth(2);
			myBoundBox.setMouseTransparent(true);
			myMap.getChildren().add(myBoundBox);
		}
	}

	public void deselect() {
		if (isSelected) {
			removeBound();
			myMap.deselectCell(this);
			isSelected = false;
		}
	}

	public void removeBound() {
		if (myBoundBox != null) {
			myMap.getChildren().remove(myBoundBox);
			myBoundBox.xProperty().unbind();
			myBoundBox.yProperty().unbind();
			myBoundBox.widthProperty().unbind();
			myBoundBox.heightProperty().unbind();
			myBoundBox = null;
		}
	}

	public boolean isSelected() {
		return isSelected;
	}

	private void showMenu(MouseEvent e) {
		myMenu.show(myMapView, e.getScreenX(), e.getScreenY());
	}

	public void removeFromMap() {
		removeBound();
		myMapView.xProperty().unbind();
		myMapView.yProperty().unbind();
		myMapView.fitWidthProperty().unbind();
		myMapView.fitHeightProperty().unbind();
		myMap.remove(this);
	}
	
	public GridPoint getPosition(){
		return myPos;
	}
	
	public ImageView getMapView(){
		return myMapView;
	}

}
