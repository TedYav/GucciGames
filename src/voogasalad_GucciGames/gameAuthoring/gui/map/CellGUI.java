package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CellGUI {
	private static Image myImage = new Image(CellGUI.class.getClassLoader().getResourceAsStream("voogasalad_GucciGames/graphics/water.jpg"));
	private ImageView myMapView;
	private ImageView myMiniView;
	private MapGrid myMap;
	private DoubleProperty mySize;
	
	public CellGUI(MapGrid map, int x, int y){
		myMap = map;
		myMapView = new ImageView(myImage);
		mySize = myMap.getCellSize();
		myMapView.fitWidthProperty().bind(mySize);
		myMapView.fitHeightProperty().bind(mySize);
		myMapView.xProperty().bind(mySize.multiply(x));
		myMapView.yProperty().bind(mySize.multiply(y));
		myMap.getChildren().add(myMapView);	
	}

}
