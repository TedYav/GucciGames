package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
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
import voogasalad_GucciGames.datastructures.Coordinate;
import voogasalad_GucciGames.datastructures.TwoWayMap;
import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.config.PlayerConfig;
import voogasalad_GucciGames.gameplayer.controller.GameControllerAdvancedInterface;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.scenes.GameScene;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;

public class MainMap extends WindowComponent implements MapInterface {

	private ResourceBundle myConfig = PlayerConfig.load("components.Map");
	private ResourceBundle myCssBundle = PlayerConfig.load("scenes.CssClasses");

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

	// TODO: leftbar and rightbar communicate about individual selection
	// TODO: later, convert to map by unit type
	private ObservableList<PlayerMapObjectInterface> mySelectedUnits;

	public MainMap(GameScene scene, GameControllerInterface controller) {
		super(scene, controller);
		initializePanes();
		initializeVariables();
		initializeMap();
		fogCells();
		drawMap(getController().getInitialState());
	}

	private void initializeVariables() {
		myCellsWide = getController().getEngine().getMapWidth();
		myCellsTall = getController().getEngine().getMapHeight();
		myCellSize = calculateCellSize();
		mySelectedUnits = FXCollections.observableArrayList();
		myUnitMap = new TwoWayMap<>();
		((GameControllerAdvancedInterface) getController()).setMap(this);
	}

	private double calculateCellSize() {
		double size = Screen.getPrimary().getBounds().getWidth() / Double.parseDouble(myConfig.getString("NumCells"));
		double stretchSize = (Screen.getPrimary().getBounds().getHeight()
				- Double.parseDouble(myConfig.getString("MapVBorder"))) / myCellsTall;
		return (size > stretchSize) ? size : stretchSize;
	}

	private void initializeMap() {
		myCellMap = new TwoWayMap<>();
		myHighlightedCells = new ArrayList<>();
		mySelectedCells = new ArrayList<>();
	}

	private void initializePanes() {
		myParent = new StackPane();
		myFirstLayer = new ScrollPane();
		myMap = new GridPane();
		mySecondLayer = new Pane();

		myParent.getChildren().add(myFirstLayer);
		myFirstLayer.setContent(myMap);
		// myParent.getChildren().add(mySecondLayer);
		//
		myFirstLayer.setVbarPolicy(ScrollBarPolicy.NEVER);
		myFirstLayer.setHbarPolicy(ScrollBarPolicy.NEVER);
		myFirstLayer.setPannable(true);
	}

	private void drawMap(List<PlayerMapObjectInterface> initialState) {
		initialState.stream().forEach(o -> addToMap(o));
		myParent.getStyleClass().add(myCssBundle.getString("mapstackpane"));
	}

	private void addToMap(PlayerMapObjectInterface object) {
		Point2D key = Coordinate.CoordinateToPoint(object.getCoordinate());
		if (!myCellMap.containsKey(key)) {
			MapCell newCell = (MapCell) Reflection.createInstance(myConfig.getString("CellClass"), getController(),
					myCellSize);
			myCellMap.put(key, newCell);
			myMap.add(newCell.getParent(),
					((Double) object.getCoordinate().getListOfCoordinates().get(0).getCenterX()).intValue(),
					((Double) object.getCoordinate().getListOfCoordinates().get(0).getCenterY()).intValue()); // TODO:
																												// get
																												// non-zero
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
	private void recenter(PlayerMapObjectInterface target) {

	}

	@Override
	public void recenter(Point2D center) {
		recenter(center.getX() / myCellsWide, center.getY() / myCellsTall);
	}

	@Override
	public void recenter(double xPercent, double yPercent) {
		myFirstLayer.setHvalue(xPercent);
		myFirstLayer.setVvalue(yPercent);
	}

	public void addUnitListener(List<ListChangeListener<PlayerMapObjectInterface>> listeners) {
		for (ListChangeListener l : listeners) {
			mySelectedUnits.addListener(l);
		}
	}

	@Override
	public void highlightCells(List<TargetCoordinateSingle> targets) {
		targets.stream()
				.map((t) -> new Point2D(t.getListOfCoordinates().get(0).getCenterX(),
						t.getListOfCoordinates().get(0).getCenterY())) // TODO:
																		// get
																		// non-zero
				.map((c) -> myCellMap.get(c)).filter((c) -> c != null).forEach((c) -> {
					c.toggleHighlight(true);
					myHighlightedCells.add(c);
				});
	}

	@Override
	public void clearHighlights() {
		myHighlightedCells.forEach((c) -> c.toggleHighlight(false));
		myHighlightedCells.clear();
	}

	@Override
	public void selectCell(MapCellInterface cell) {
		clearActiveCells();
		for (Integer i : cell.getUnits().keySet()) {
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
		result.stream().forEach(u -> {
			if (u.isRemoved()) {
				removeUnitFromMap(u);
			}
		});
		update();
	}

	private void redrawUnit(PlayerMapObjectInterface unit) {
		removeUnitFromMap(unit);
		addToMap(unit);
	}

	private void removeUnitFromMap(PlayerMapObjectInterface unit) {
		if (myUnitMap.containsValue(unit)) {
			myUnitMap.getKey(unit).removeObject(unit);
			myUnitMap.remove(unit);
			myUnitMap.removeKey(unit);
		}
	}

	@Override
	public Point2D getCellCoordinate(MapCellInterface cell) {
		return myCellMap.getKey(cell);
	}

	@Override
	public MapCellInterface getCell(Point2D coordinate) {
		return myCellMap.get(coordinate);
	}

	@Override
	public List<Double> getVisibleArea() {
		double numCellsWide = (Screen.getPrimary().getBounds().getWidth()
				- Double.parseDouble(myConfig.getString("MapHBorder"))) / myCellSize;
		double numCellsTall = (Screen.getPrimary().getBounds().getHeight()
				- Double.parseDouble(myConfig.getString("MapVBorder"))) / myCellSize;
		double myPercentWide = numCellsWide / myCellsWide;
		double myPercentTall = numCellsTall / myCellsTall;
		return Arrays.asList(myPercentWide, myPercentTall);
	}

	@Override
	public void addHListener(ChangeListener<? super Number> listener) {
		myFirstLayer.hvalueProperty().addListener(listener);
	}

	@Override
	public void addVListener(ChangeListener<? super Number> listener) {
		myFirstLayer.vvalueProperty().addListener(listener);
	}
}
