package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;

public class MiniMap extends Observable implements MiniMapInterface {

	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.MiniMap");
	
	private GridPane myPane;
	
	private Map<Point2D, ImageView> myCells;
	private Rectangle myOverlay;
	
	private int myWidth, myHeight;
	private int myCellWidth, myCellHeight;
	
	public MiniMap(int width, int height){
		myWidth = width;
		myHeight = height;
		myCells = new HashMap<>(myWidth * myHeight);
		myCellWidth = Integer.parseInt(myConfig.getString("Width"))/myWidth;
		myCellHeight = Integer.parseInt(myConfig.getString("Height"))/myHeight;
		initializePane();
	}
	
	private void initializePane(){
		myPane = new GridPane();
	}
	
	@Override
	public void initialize(Map<Point2D, String> images){
		
	}
	
	public void recenter(MapCell cell) {
		// TODO Auto-generated method stub
		
	}

	public void recenter(Point2D coordinate) {
		// TODO Auto-generated method stub
		
	}

}
