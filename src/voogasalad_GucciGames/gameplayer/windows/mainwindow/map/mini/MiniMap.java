package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public class MiniMap implements MiniMapInterface {

	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.MiniMap");
	
	private StackPane myStackPane;
	private GridPane myGridPane;
	private Pane myOverlayPane;
	
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
		initializePanes();
	}
	
	public Parent getParent(){
		return myStackPane;
	}
	
	private void initializePanes(){
		myStackPane = new StackPane();
		myGridPane = new GridPane();
		myOverlayPane = new Pane();
		myStackPane.getChildren().add(myGridPane);
		myStackPane.getChildren().add(myOverlayPane);
	}
	
	@Override
	public void initialize(Map<Point2D, String> images){
		
	}
	
	public void recenter(MapCellInterface cell) {
		// TODO Auto-generated method stub
		
	}

	public void recenter(Point2D coordinate) {
		// TODO Auto-generated method stub
		
	}

}
