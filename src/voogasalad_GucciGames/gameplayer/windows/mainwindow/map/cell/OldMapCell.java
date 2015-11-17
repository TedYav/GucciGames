package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameplayer.controller.dummy.MapObjectBasicType;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;

public abstract class OldMapCell implements MapCellInterface {

	// TODO: factor this into two or three classes eventually
	private Point2D myCoordinate;
	
	private StackPane myParent;
	protected GridPane myGround;
	protected GridPane myStructures;
	protected GridPane myUnits;
	private StackPane myOverlays;
	
	protected Shape myActiveOverlay;
	protected Shape myFogOverlay;
	
	private GameControllerInterface myController;
	
	private double mySize;
	
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.MapCell");
	
	private List<PlayerMapObjectInterface> myObjects;
	
	public OldMapCell(GameControllerInterface controller, double myCellSize){
		initializeVariables(controller, myCellSize);
		initializePanes();
		initializeOverlays();
	}
	
	private void initializeOverlays() {
		// TODO Auto-generated method stub
		
	}

	private void initializeVariables(GameControllerInterface controller, double myCellSize){
		myController = controller;
		mySize = myCellSize;
	}
		
	private void initializePanes() {
		myParent = new StackPane();
		myGround = new GridPane();
		myStructures = new GridPane();
		myUnits = new GridPane();
		myOverlays = new StackPane();
		myParent.getChildren().addAll(myGround, myStructures, myUnits, myOverlays);
	}

	public Parent getParent(){
		return myParent;
	}
	
	public void addObject(PlayerMapObjectInterface object){
		
	}
	
	public void removeObject(PlayerMapObjectInterface object){
		
	}
	
	@Override
	public void activate() {
		
	}

	@Override
	public void hover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleFog(boolean fog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleHighlight(boolean highlight) {
		// TODO Auto-generated method stub
		
	}

	private void redraw(){
		drawGround();
		drawStructures();
		drawUnits();
	}

	private void drawUnits() {
		// TODO Auto-generated method stub
		
	}

	private void drawStructures() {
		// TODO Auto-generated method stub
		
	}

	private void drawGround() {
		// TODO Auto-generated method stub
		
	}
	
}
