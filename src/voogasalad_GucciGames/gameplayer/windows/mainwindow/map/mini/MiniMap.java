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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.datastructures.ImageAverager;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public class MiniMap extends WindowComponent implements MiniMapInterface, Observer {

	private Map<Point2D, Rectangle> myShapeMap;	
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.MiniMap");
	
	private StackPane myStackPane;
	private GridPane myGrid;
	private Pane myOverlayPane;
	private Rectangle myOverlay;
	
	private ImageAverager myImageAverager;
	
	private int myWidth, myHeight;
	private int myCellWidth, myCellHeight;
	
	public MiniMap(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		initializeVariables();
		initializePanes();
		initializeMap();
	}

	private void initializeMap() {
		
		for(int x=0; x<myWidth; x++){
			for(int y=0; y<myHeight; y++){
				Point2D coord = new Point2D(x,y);
				makeShape(coord);
				colorShape(coord);
				registerObserver(coord);
			}
		}
	}

	private void registerObserver(Point2D coord) {
		getController().getMap().getCell(coord).addObserver(this);
	}

	private void colorShape(Point2D coord) {
		Color color = Color.BLACK;
		List<String> cellContents = getController().getMap().getCell(coord).getImageList();
		myShapeMap.get(coord).setFill(color);
	}

	private void makeShape(Point2D coord) {
		myShapeMap.put(coord, new Rectangle(myCellWidth, myCellHeight));
	}

	private void initializeVariables() {
		myWidth = getController().getEngine().getMapWidth();
		myHeight = getController().getEngine().getMapHeight();
		myShapeMap = new HashMap<>();
		myCellWidth = Integer.parseInt(myConfig.getString("Width"))/myWidth;
		myCellHeight = Integer.parseInt(myConfig.getString("Height"))/myHeight;
		myImageAverager = new ImageAverager(getController());
	}
	
	public Parent getParent(){
		return myStackPane;
	}
	
	private void initializePanes(){
		myStackPane = new StackPane();
		myGrid = new GridPane();
		myOverlayPane = new Pane();
		myStackPane.getChildren().add(myGrid);
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

	public void redraw(){
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		MapCellInterface cell = (MapCellInterface)o;
		colorShape(getController().getMap().getCellCoordinate(cell));
	}

}
