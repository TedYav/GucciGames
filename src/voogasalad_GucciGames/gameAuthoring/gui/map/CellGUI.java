package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CellGUI {
	private ImageView myImageView;
	private IGuiMap myMap;
	private DoubleProperty mySize;
	
	public CellGUI(IGuiMap map, int x, int y){
		myMap = map;
		myImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("voogasalad_GucciGames/graphics/water.jpg")));
		mySize = myMap.getCellSizeProperty();
		myImageView.fitWidthProperty().bind(mySize);
		myImageView.fitHeightProperty().bind(mySize);
		myImageView.xProperty().bind(mySize.multiply(x));
		myImageView.yProperty().bind(mySize.multiply(y));
		myMap.add(myImageView);
		
	}

}
