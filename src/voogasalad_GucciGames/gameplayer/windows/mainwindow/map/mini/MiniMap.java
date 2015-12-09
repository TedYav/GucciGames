package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
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
	
	private double myOverlayWidth;
	private double myOverlayHeight;
	
	
	public MiniMap(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		
		initializeVariables();
		initializePanes();
		initializeMap();
		initializeOverlay();
		initializeHandler();
		initializeListeners();
	}

	private void initializeListeners() {
		getController().getMap().addHListener( (o, ov, nv) -> scrollH(o, ov, nv));
		getController().getMap().addVListener( (o, ov, nv) -> scrollV(o, ov, nv));
	}

	private void scrollV(ObservableValue<? extends Number> o, Number ov, Number nv) {
		myOverlay.setTranslateY(nv.doubleValue()*yTranslateLimit());
	}

	private void scrollH(ObservableValue<? extends Number> o, Number ov, Number nv) {
		myOverlay.setTranslateX(nv.doubleValue()*xTranslateLimit());
	}

	private void initializeOverlay() {
		myOverlayPane = new Pane();
		List<Double> overlayDimensions = getOverlaySize();
		myOverlayWidth = overlayDimensions.get(0);
		myOverlayHeight = overlayDimensions.get(1);
		myOverlay = new Rectangle(myOverlayWidth, myOverlayHeight);
		double maxSize = Double.parseDouble(myConfig.getString("MaxOverlaySize"));
		//double opacity = (myOverlayWidth > maxSize && myOverlayHeight > maxSize)?0.0:Double.parseDouble(myConfig.getString("OverlayOpacity"));
		//myOverlay.setFill(Color.web(myConfig.getString("OverlayColor"), opacity));
		if(!(myOverlayWidth > maxSize && myOverlayHeight > maxSize)){
			myOverlay.getStyleClass().add("minimap-overlay");
		}else{
			myOverlay.setOpacity(0.0);
		}
		myStackPane.getChildren().add(myOverlayPane);
		myOverlayPane.getChildren().add(myOverlay);
	}

	private List<Double> getOverlaySize() {
		List<Double> overlays = getController().getMap().getVisibleArea();
		return Arrays.asList(overlays.get(0)*myWidth*myCellWidth, overlays.get(1)*myHeight*myCellHeight);
	}

	private void initializeHandler() {
		myOverlayPane.setOnMouseDragged( e -> recenter(e));
		myOverlayPane.setOnMouseDragReleased( e -> endDrag(e));
	}

	private void endDrag(MouseDragEvent e) {
		getGameScene().getScene().setCursor(Cursor.DEFAULT);

	}

	private void recenter(MouseEvent e) {
		//getGameScene().getScene().setCursor(Cursor.NONE);

		double percX = xPercent(e.getX());
		double percY = yPercent(e.getY());
		if(percX <= 1.1 && percX >= -.1)
			myOverlay.setTranslateX( percX  * xTranslateLimit());
		if(percY <= 1.1 && percY >= -.1)
			myOverlay.setTranslateY( percY * yTranslateLimit());
		
		
		getController().getMap().recenter(xPercent(e.getX()), yPercent(e.getY()));
	}

	private double yTranslateLimit() {
		return (myHeight*myCellHeight)-myOverlayHeight;
	}

	private double xTranslateLimit() {
		double myXTranslateLimit =  ((myWidth*myCellWidth)-myOverlayWidth);
		return myXTranslateLimit;
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
