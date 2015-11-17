package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.main;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.dummy.DummyTile;
import voogasalad_GucciGames.gameplayer.controller.dummy.DummyUnit;
import voogasalad_GucciGames.gameplayer.datastructures.Coordinate;
import voogasalad_GucciGames.gameplayer.datastructures.TwoWayMap;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.SquareCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini.MiniMap;
import voogasalad_GucciGames.testing.TestPlayer;

public class MainMap extends WindowComponent implements MapInterface {
	
	private MiniMap myMiniMap;
	
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.Map");

	private TwoWayMap<Point2D, MapCellInterface> myCellMap;
	private List<MapCellInterface> myHighlightedCells;
	private List<MapCellInterface> mySelectedCells;
	private TwoWayMap<MapCellInterface, PlayerMapObjectInterface> myUnitMap;

	
	private StackPane myParent;
	private ScrollPane myFirstLayer;
	private GridPane myMap;
	private Pane mySecondLayer;
	
	// TODO: factor into cell style, later
	private int myCellsWide, myCellsTall;
	private double myCellSize;
	private double myBorderWidth;
	
	// TODO: leftbar and rightbar communicate about individual selection
	// TODO: later, convert to map by unit type
	private ObservableList<PlayerMapObjectInterface> mySelectedUnits;
	
	public MainMap(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		initializePanes();
		initializeVariables();
		initializeMap();
		initializeMiniMap();
		drawMap(TestPlayer.getDummyMap(50));
	}
	
	
	private void initializeVariables() {
		myCellSize = Screen.getPrimary().getBounds().getWidth()/Double.parseDouble(myConfig.getString("NumCells"));
		myCellsWide = 50;
		myCellsTall = 50;
		myBorderWidth = Double.parseDouble(myConfig.getString("BorderWidth"));
		mySelectedUnits = FXCollections.observableArrayList();
		myUnitMap = new TwoWayMap<>();
		myController.setMap(this);
	}

	private void initializeMap() {
		myCellMap = new TwoWayMap<>();
		myHighlightedCells = new ArrayList<>();
		mySelectedCells = new ArrayList<>();
		
		// query width, size, etc
		// myGame.etc etc etc
	}
	
	private void initializePanes(){
		myParent = new StackPane();
		myFirstLayer = new ScrollPane();
		myMap = new GridPane();
		mySecondLayer = new Pane();
		
		myParent.getChildren().add(myFirstLayer);
		myFirstLayer.setContent(myMap);
//		myParent.getChildren().add(mySecondLayer);
//		
		myFirstLayer.setVbarPolicy(ScrollBarPolicy.NEVER);
		myFirstLayer.setHbarPolicy(ScrollBarPolicy.NEVER);
		myFirstLayer.setPannable(true);
	}
	
	private void initializeMiniMap(){
		myMiniMap = new MiniMap(myCellsWide, myCellsTall);
		mySecondLayer.getChildren().add(myMiniMap.getParent());
	}
	
	//TODO: add loading bar
	
	private void drawMap(List<PlayerMapObjectInterface> initialState){
		initialState.stream()
			.forEach(o->addToMap(o));
        myParent.getStyleClass().add(myConfig.getString("MainCSSClass"));
        myParent.applyCss();
        myMap.getStyleClass().add(myConfig.getString("MainCSSClass"));
        myMap.applyCss();
	}
	
	private void addToMap(PlayerMapObjectInterface object) {
		Point2D key = Coordinate.CoordinateToPoint(object.getCoordinate());
		if(!myCellMap.containsKey(key)){
			MapCell newCell = (MapCell)Reflection.createInstance(myConfig.getString("CellClass"), myController, myCellSize);
			myCellMap.put(key, newCell);
			myMap.add(newCell.getParent(), ((Double)object.getCoordinate().getListOfCoordinates().get(0).getCenterX()).intValue(), ((Double)object.getCoordinate().getListOfCoordinates().get(0).getCenterY()).intValue());
		}
		MapCellInterface target = myCellMap.get(key);
		target.addObject(object);
		myUnitMap.put(target, object);
	}

	@Override
	public Parent getParent() {
		return myParent;
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
	public void highlightCells(List<TargetCoordinateSingle> targets) {
		targets.stream()
			.map((t) -> new Point2D(t.getListOfCoordinates().get(0).getCenterX(), t.getListOfCoordinates().get(0).getCenterY()))
			.map((c) -> myCellMap.get(c))
			.filter((c) -> c!=null)
			.forEach((c) -> { c.toggleHighlight(true); myHighlightedCells.add(c);} );
	}
	
	@Override
	public void clearHighlights(){
		myHighlightedCells.forEach((c) -> c.toggleHighlight(false) );
		myHighlightedCells.clear();
	}


	@Override
	public void selectCell(MapCellInterface cell) {
		clearActiveCells();
	    for (Integer i: cell.getUnits().keySet()) {
	        mySelectedUnits.addAll(cell.getUnits().get(i));
	    }
	    mySelectedCells.add(cell);
	}


	@Override
	public List<MapCellInterface> getSelectedCells() {
		return mySelectedCells;
	}


	@Override
	public void clearActiveCells() {
		mySelectedCells.forEach(c -> c.deactivate());
		mySelectedUnits.clear();
		mySelectedCells.clear();
	}


	@Override
	public void update(List<PlayerMapObjectInterface> result) {
		result.stream().forEach(u -> redrawUnit(u));
		update();
	}


	private void redrawUnit(PlayerMapObjectInterface unit) {
		if(myUnitMap.containsValue(unit)){
			myUnitMap.getKey(unit).removeObject(unit);
			myUnitMap.remove(unit);
			myUnitMap.removeKey(unit);
		}
		addToMap(unit);
	}


	@Override
	public Point2D getCellCoordinate(MapCellInterface cell) {
		return myCellMap.getKey(cell);
	}


	@Override
	public MapCellInterface getCell(Point2D coordinate) {
		return myCellMap.get(coordinate);
	}
}
