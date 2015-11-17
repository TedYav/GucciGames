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
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.dummy.DummyTile;
import voogasalad_GucciGames.gameplayer.controller.dummy.TargetCoordinate;
import voogasalad_GucciGames.gameplayer.datastructures.TwoWayMap;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.MapCellInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.SquareCell;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.mini.MiniMap;

public class MainMap extends WindowComponent implements MapInterface {

	private MiniMap myMiniMap;
	
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.Map");

	private TwoWayMap<Point2D, MapCell> myCellMap;
	private List<MapCellInterface> myHighlightedCells;
	private List<MapCellInterface> mySelectedCells;

	
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
		drawMap();
	}
	
	
	private void initializeVariables() {
		myCellSize = Screen.getPrimary().getBounds().getWidth()/Double.parseDouble(myConfig.getString("NumCells"));
		myCellsWide = 50;
		myCellsTall = 50;
		myBorderWidth = Double.parseDouble(myConfig.getString("BorderWidth"));
		mySelectedUnits = FXCollections.observableArrayList();
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
	    private void drawMap(){
	        //myMap.setStyle("-fx-background-color: red");
	        //myMap.setMinWidth(myCellsWide * myCellSize);
	        //myMap.setMinHeight(myCellsTall * myCellSize);
	        //              for(int i=0; i<myCellsWide; i++){
	        //                      for(int j=0; j<myCellsTall; j++){
	        //                              Rectangle r = new Rectangle();
	        //                              r.setWidth(myCellSize);
	        //                              r.setHeight(myCellSize);
	        //                              r.setFill(((i+j)%2==0)?Color.WHEAT:Color.RED);
	        //                              myMap.add(r, i, j);
	        //                      }
	        //              }
	        for(int i=0; i<myCellsWide; i++){
	            for(int j=0; j<myCellsTall; j++){
	                MapCell c = new SquareCell(myController, myCellSize);
	                c.getParent().setOnMouseClicked(e->{
	                    updateSelectedUnits(c);
	                });
	                c.addObject(new DummyTile(i,j));
	                myCellMap.put(new Point2D(i,j), c);
	                myMap.add(c.getParent(), i, j);
	            }
	        }
	        List<PlayerMapObjectInterface> initialState = myController.getInitialState();
	        ATargetCoordinate targCoordBuffer;
	        for (PlayerMapObjectInterface mapObj: initialState) {
	            targCoordBuffer = mapObj.getCoordinate();
	            if (targCoordBuffer.getClass().equals(TargetCoordinateSingle.class)){
	                TargetCoordinateSingle single = (TargetCoordinateSingle)targCoordBuffer;
	                myCellMap.get(new Point2D(single.getCenterX(),single.getCenterY())).addObject(mapObj);
	            }
	            else if (targCoordBuffer.getClass().equals(TargetCoordinateMultiple.class)) {
	                TargetCoordinateMultiple multiple = (TargetCoordinateMultiple)targCoordBuffer;
	                for (TargetCoordinateSingle single: multiple.getCoordinates()) {
	                    myCellMap.get(new Point2D(single.getCenterX(),single.getCenterY())).addObject(mapObj);//TODO: multiple coords
	                }
	            }
	        }
        myParent.getStyleClass().add(myConfig.getString("MainCSSClass"));
        myParent.applyCss();
        myMap.getStyleClass().add(myConfig.getString("MainCSSClass"));
        myMap.applyCss();
	}
	
	@Override
	public Parent getParent() {
		return myParent;
	}

	private void fogCells() {
		
	}
	
	    private void updateSelectedUnits(MapCell cell) {
	        mySelectedUnits.clear();
	        System.out.println("sfdsaf");
	        for (Integer i: cell.getUnits().keySet()) {
	            mySelectedUnits.addAll(cell.getUnits().get(i));
	        }
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
	public void highlightCells(List<TargetCoordinate> targets) {
		targets.stream()
			.map((t) -> new Point2D(t.getX(), t.getY()))
			.map((c) -> myCellMap.get(c))
			.forEach((c) -> { c.toggleHighlight(true); myHighlightedCells.add(c);} );
	}
	
	@Override
	public void clearHighlights(){
		myHighlightedCells.forEach((c) -> { c.toggleHighlight(false); myHighlightedCells.remove(c); } );
	}


	@Override
	public void selectCell(MapCellInterface cell) {
		System.out.println("SELECTING");
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
		System.out.println("CLEARING");
		mySelectedCells.forEach(c -> c.deactivate());
		mySelectedUnits.clear();
		mySelectedCells.clear();
	}


	@Override
	public void update(List<PlayerMapObjectInterface> result) {
		// TODO Auto-generated method stub
		
	}
}
