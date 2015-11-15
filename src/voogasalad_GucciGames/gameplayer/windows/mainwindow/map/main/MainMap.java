package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.sun.javafx.scene.traversal.Direction;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameplayer.controller.GameEngineInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.CellUnit;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini.MiniMap;

public class MainMap extends WindowComponent implements MapInterface {
	
	private MiniMap myMiniMap;
	
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.Map");
	protected Map<PlayerMapObjectInterface, CellUnit> myUnitMap;
	protected Map<Point2D, MapCell> myCellMap;
	
	private StackPane myStackPane;
	private Pane myFirstLayer;
	private GridPane myMap;
	private Pane mySecondLayer;
	
	private int myCellsWide, myCellsTall;
	private double myCellSize;
	private double myBorderWidth;
	private double myMoveDistance;
	
	public MainMap(GameScene scene, GameEngineInterface game) {
		super(scene, game);
		initializeVariables();
		initializeMap();
		initializePanes();
		initializeMiniMap();
		drawMap();
	}
	
	
	private void initializeVariables() {
		myCellSize = Double.parseDouble(myConfig.getString("CellSize"));
		myCellsWide = 100;
		myCellsTall = 100;
		myBorderWidth = Double.parseDouble(myConfig.getString("BorderWidth"));
		myMoveDistance = Double.parseDouble(myConfig.getString("MoveDistance"));
				
	}

	private void initializeMap() {
		myUnitMap = new HashMap<>();
		myCellMap = new HashMap<>();
		
		// query width, size, etc
		// myGame.etc etc etc
	}
	
	private void initializePanes(){
		myStackPane = new StackPane();
		myFirstLayer = new Pane();
		myMap = new GridPane();
		mySecondLayer = new Pane();
		
		myStackPane.getChildren().add(myFirstLayer);
		myFirstLayer.getChildren().add(myMap);
		myStackPane.getChildren().add(mySecondLayer);
	}
	
	private void initializeMiniMap(){
		myMiniMap = new MiniMap(myCellsWide, myCellsTall);
		mySecondLayer.getChildren().add(myMiniMap.getParent());
	}
	
	private void drawMap(){
		//myMap.setStyle("-fx-background-color: red");
		myMap.setMinWidth(myCellsWide * myCellSize);
		myMap.setMinHeight(myCellsTall * myCellSize);
		for(int i=0; i<myCellsWide; i++){
			for(int j=0; j<myCellsTall; j++){
				Rectangle r = new Rectangle();
				r.setWidth(myCellSize);
				r.setHeight(myCellSize);
				r.setFill(((i+j)%2==0)?Color.WHEAT:Color.RED);
				myMap.add(r, i, j);
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
	public void activateCell(MapCell cell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateCell(Point2D coordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapCell getCell(Point2D coordinate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(KeyCode direction) {
		double translateX = convertCode(direction, KeyCode.LEFT, KeyCode.RIGHT);
		double translateY = convertCode(direction, KeyCode.UP, KeyCode.DOWN);
				
		myMap.setTranslateX(myMap.getTranslateX()+translateX);
		myMap.setTranslateY(myMap.getTranslateY()+translateY);
	}
	
	private double convertCode(KeyCode direction, KeyCode negative, KeyCode positive){
		if(direction == negative || direction == positive){
			return ((direction == positive)?1:-1)*(myMoveDistance*myCellSize);
		}
		return 0;
	}

	@Override
	public void fogCells(List<Point2D> targets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfogCells() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapCell moveObjectToCell(MapCell target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapCell moveObjectToCell(Point2D target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recenter(Point2D center) {
		// TODO Auto-generated method stub
		
	}
}
