package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.sun.javafx.scene.traversal.Direction;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.datastructures.TwoWayMap;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.DummyUnit;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.SquareCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini.MiniMap;

public class MainMap extends WindowComponent implements MapInterface {
	
	private MiniMap myMiniMap;
	
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.Map");
	private TwoWayMap<Point2D, MapCell> myCellMap;
	private Map<String, Image> myImageMap;
	private List<MapCell> myHighlightedCells;
	
	private StackPane myStackPane;
	private ScrollPane myFirstLayer;
	private GridPane myMap;
	private Pane mySecondLayer;
	
	// TODO: factor into cell style, later
	private int myCellsWide, myCellsTall;
	private double myCellSize;
	private double myBorderWidth;
	private double myMoveDistance;
	
	// TODO: leftbar and rightbar communicate about individual selection
	// TODO: later, convert to map by unit type
	private ObservableList<PlayerMapObjectInterface> mySelectedUnits;
	
	public MainMap(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		initializeVariables();
		initializeMap();
		initializePanes();
		initializeMiniMap();
		drawMap();
	}
	
	
	private void initializeVariables() {
		myCellSize = Double.parseDouble(myConfig.getString("CellSize"));
		myCellsWide = 50;
		myCellsTall = 50;
		myBorderWidth = Double.parseDouble(myConfig.getString("BorderWidth"));
		myMoveDistance = Double.parseDouble(myConfig.getString("MoveDistance"));
		mySelectedUnits = FXCollections.observableArrayList();
	}

	private void initializeMap() {
		myCellMap = new TwoWayMap<>();
		
		// query width, size, etc
		// myGame.etc etc etc
	}
	
	private void initializePanes(){
		myStackPane = new StackPane();
		myFirstLayer = new ScrollPane();
		myMap = new GridPane();
		mySecondLayer = new Pane();
		
		myStackPane.getChildren().add(myFirstLayer);
		myFirstLayer.setContent(myMap);
		myStackPane.getChildren().add(mySecondLayer);
		
		myFirstLayer.setVbarPolicy(ScrollBarPolicy.NEVER);
		myFirstLayer.setHbarPolicy(ScrollBarPolicy.NEVER);
		myFirstLayer.setPannable(true);
	}
	
	private void initializeMiniMap(){
		myMiniMap = new MiniMap(myCellsWide, myCellsTall);
		mySecondLayer.getChildren().add(myMiniMap.getParent());
	}
	
	private void drawMap(){
		//myMap.setStyle("-fx-background-color: red");
		//myMap.setMinWidth(myCellsWide * myCellSize);
		//myMap.setMinHeight(myCellsTall * myCellSize);
//		for(int i=0; i<myCellsWide; i++){
//			for(int j=0; j<myCellsTall; j++){
//				Rectangle r = new Rectangle();
//				r.setWidth(myCellSize);
//				r.setHeight(myCellSize);
//				r.setFill(((i+j)%2==0)?Color.WHEAT:Color.RED);
//				myMap.add(r, i, j);
//			}
//		}
		for(int i=0; i<myCellsWide; i++){
			for(int j=0; j<myCellsTall; j++){
				MapCell c = new SquareCell(myController, myCellSize);
				c.addObject(new DummyUnit(i,j));
				myCellMap.put(new Point2D(i,j), c);
				myMap.add(c.getParent(), i, j);
			}
		}
        myStackPane.getStyleClass().add(myConfig.getString("MainCSSClass"));
        myStackPane.applyCss();
        myMap.getStyleClass().add(myConfig.getString("MainCSSClass"));
        myMap.applyCss();
	}
	
	@Override
	public Parent getParent() {
		return myStackPane;
	}

	@Override
	public void move(KeyCode direction) {
		double translateX = convertCode(direction, KeyCode.LEFT, KeyCode.RIGHT);
		double translateY = convertCode(direction, KeyCode.UP, KeyCode.DOWN);

		if(allowMove(translateX, myMap.getTranslateX(), myMap.getLayoutBounds().getMaxX() - myFirstLayer.getLayoutBounds().getMaxX())){
			myMap.setTranslateX(myMap.getTranslateX()+translateX);
		}
		
		if(allowMove(translateY, myMap.getTranslateY(), myMap.getLayoutBounds().getMaxY() - myFirstLayer.getLayoutBounds().getMaxY())){
			myMap.setTranslateY(myMap.getTranslateY()+translateY);
		}	
	}
	
	private boolean allowMove(double translate, double current, double max){
		return (translate > 0)?(current < 0):(-(current))<(max-Math.abs(translate/2));
	}
	
	private double convertCode(KeyCode direction, KeyCode negative, KeyCode positive){
		if(direction == negative || direction == positive){
			return ((direction == positive)?1:-1)*(myMoveDistance*myCellSize);
		}
		return 0;
	}

	private void fogCells() {
		
	}
	
	@Override
	public void update() {
		clearActiveCells();
		clearHighlights();
		fogCells();
	}
	
	@Override
	public void redrawFog() {
		
	}
	
	// for doing animations and such
	private void recenter(PlayerMapObjectInterface target){
		
	}

	@Override
	public void recenter(Point2D center) {
		
	}
	
	public void addUnitListener(ListChangeListener<PlayerMapObjectInterface> listener){
		mySelectedUnits.addListener(listener);
	}


	@Override
	public Image requestImage(String URI) {
		if(!myImageMap.containsKey(URI)){
			myImageMap.put(URI, new Image(URI));
		}
		return myImageMap.get(URI);
	}


	@Override
	public void highlightCell(Point2D target) {
		myCellMap.get(target).toggleHighlight(true);
		myHighlightedCells.add(myCellMap.get(target));
	}
	
	@Override
	public void clearHighlights(){
		myHighlightedCells.forEach((c) -> { c.toggleHighlight(false); myHighlightedCells.remove(c); } );
	}


	@Override
	public void activateCell(MapCellInterface cell) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<MapCellInterface> getActiveCells() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void clearActiveCells() {
		// TODO Auto-generated method stub
		
	}
}
