package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;
import voogasalad_GucciGames.helpers.ImageAverager;

public class MiniMap extends DisplayComponent implements MiniMapInterface, Observer {

	private Map<Point2D, Rectangle> myShapeMap;	
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.MiniMap");
	
	private StackPane myStackPane;
	private GridPane myGrid;
	private Pane myOverlayPane;
	private Rectangle myOverlay;
		
	private int myWidth, myHeight;
	private int myCellWidth, myCellHeight;
	
	public MiniMap(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		initializeVariables();
		initializePanes();
		initializeMap();
		initializeHandler();
	}

	private void initializeHandler() {
		myGrid.setOnMouseDragged( e -> recenter(e));
	}

	private void recenter(MouseEvent e) {
		getController().getMap().recenter(xPercent(e.getX()), yPercent(e.getY()));
	}

	private double yPercent(double x) {
		return x/(myCellWidth*myWidth);
	}

	private double xPercent(double y) {
		return y/(myCellHeight*myHeight);

	}

	private void initializeMap() {
		
		for(int x=0; x<myWidth; x++){
			for(int y=0; y<myHeight; y++){
				Point2D coord = new Point2D(x,y);
				MapCellInterface cell = getController().getMap().getCell(coord);				
				myShapeMap.put(coord, new Rectangle(myCellWidth, myCellHeight));
				myShapeMap.get(coord).setFill(cell.getColor());
				cell.addObserver(this);
				myGrid.add(myShapeMap.get(coord), x, y);
				//myShapeMap.get(coord).setOnMouseClicked( e -> recenter(coord));
			}
		}
	}

	private void initializeVariables() {
		myWidth = getController().getEngine().getMapWidth();
		myHeight = getController().getEngine().getMapHeight();
		myShapeMap = new HashMap<>();
		myCellWidth = Integer.parseInt(myConfig.getString("Width"))/myWidth;
		myCellHeight = Integer.parseInt(myConfig.getString("Height"))/myHeight;
	}
	
	public Parent getParent(){
		return myStackPane;
	}
	
	private void initializePanes(){
		myStackPane = new StackPane();
		myGrid = new GridPane();
		//myOverlayPane = new Pane();
		myStackPane.getChildren().add(myGrid);
		//myStackPane.getChildren().add(myOverlayPane);
	}
	
	@Override
	public void initialize(Map<Point2D, String> images){
		
	}
	
	public void recenter(MapCellInterface cell) {
		// TODO Auto-generated method stub
		
	}

	public void recenter(Point2D coordinate) {
		getController().getMap().recenter(coordinate);
	}

	public void redraw(){
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		MapCellInterface cell = (MapCellInterface)o;
		myShapeMap.get( getController().getMap().getCellCoordinate(cell) ).setFill(cell.getColor());
	}

}
