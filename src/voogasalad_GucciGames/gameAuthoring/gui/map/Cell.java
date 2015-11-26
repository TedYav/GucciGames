package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class Cell {
	private ImageView myMapView;
	private ImageView myMiniView;
	private Rectangle myBoundBox;
	private Grid myMap;
	private DoubleProperty mySize;
	private PopupMenu myMenu;
	private boolean isSelected = false;
	private GridPoint myPos;
	private MapObject myMapObject;

	public Cell(Grid map, int x, int y) {
		myMap = map;
		myMapView = new ImageView();
		mySize = myMap.getCellSize();
		myMapView.fitWidthProperty().bind(mySize);
		myMapView.fitHeightProperty().bind(mySize);
		myMapView.xProperty().bind(mySize.multiply(x));
		myMapView.yProperty().bind(mySize.multiply(y));
		myMapView.setOnMouseClicked(e -> mouseClickEvent(e));
		myPos = new GridPoint(x, y);
		myMap.add(this);
	}

	public void setImage(Image img, MapObject mapType) {
		myMapView.setImage(img);
		
		double width = mapType.getWidth();
		double height = mapType.getHeight();
		double myX1 = mapType.getX()*width;
		double myY1 = mapType.getY()*height;
		Rectangle2D rect = new Rectangle2D(myX1, myY1, width, height);
		myMapView.setViewport(rect);
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
		if(myMenu==null)
			myMenu = new PopupMenu(myMap.getController(), this);
		myMenu.update();
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
	
	public void setObject(MapObject obj){
		myMapObject = obj;
	}
	
	public MapObject getObject(){
		return myMapObject;
	}

}
