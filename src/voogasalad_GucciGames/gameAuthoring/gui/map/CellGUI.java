package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellGUI {
	private static Image myImage = new Image(
			CellGUI.class.getClassLoader().getResourceAsStream("voogasalad_GucciGames/graphics/water.png"));
	private ImageView myMapView;
	private ImageView myMiniView;
	private Rectangle myBoundBox;
	private Grid myMap;
	private DoubleProperty mySize;
	private ContextMenu myMenu;

	public CellGUI(Grid map, int x, int y) {
		myMap = map;
		myMapView = new ImageView(myImage);
		mySize = myMap.getCellSize();
		myMapView.fitWidthProperty().bind(mySize);
		myMapView.fitHeightProperty().bind(mySize);
		myMapView.xProperty().bind(mySize.multiply(x));
		myMapView.yProperty().bind(mySize.multiply(y));
		myMap.getChildren().add(myMapView);
		myMapView.setOnMouseClicked(e -> mouseClickEvent(e));
		createMenu();
	}
	
	public void setImage(Image img){
		//myMapView.setImage(img);
	}

	private void createMenu() {
		MenuItem item1 = new MenuItem("Edit");
		item1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Edit");
			}
		});
		MenuItem item2 = new MenuItem("Remove");
		item2.setOnAction(e->removeFromMap());
		myMenu =  new ContextMenu(item1, item2);

	}

	private void mouseClickEvent(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			showBound();
			myMap.addSelectedCell(this);
		} else if (e.getButton() == MouseButton.SECONDARY) {
			showMenu(e);
		}

	}

	private void showBound() {
		if (myBoundBox == null) {
			myBoundBox = new Rectangle(myMapView.getX(), myMapView.getY(), mySize.get(), mySize.get());
			myBoundBox.setStroke(Color.YELLOW);
			myBoundBox.setFill(Color.TRANSPARENT);
			myBoundBox.setStrokeWidth(2);
			myBoundBox.setMouseTransparent(true);
			myMap.getChildren().add(myBoundBox);
		} else {
			myMap.getChildren().remove(myBoundBox);
			myBoundBox = null;
		}
	}

	private void showMenu(MouseEvent e) {
		myMenu.show(myMapView, e.getScreenX(), e.getScreenY());
	}
	
	public void removeFromMap(){
		if(myBoundBox!=null){
			myMap.getChildren().remove(myBoundBox);
			myBoundBox=null;
		}
		myMap.getChildren().remove(myMapView);
	}

}
