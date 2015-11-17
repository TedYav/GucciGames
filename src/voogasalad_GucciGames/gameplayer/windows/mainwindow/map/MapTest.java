package voogasalad_GucciGames.gameplayer.windows.mainwindow.map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class MapTest extends Application{

	private BorderPane myRoot;
	
	private Scene myScene;
	private StackPane myParent;
	private AnchorPane myFirstLayer;
	private GridPane myMap;
	
	private StackPane myCell;
	private GridPane myCellLayer;
	private GridPane myOverlayLayer;
	private Shape myOverlay;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.show();
		
		myRoot = new BorderPane();
		myParent = new StackPane();
		myRoot.setCenter(myParent);
		
		myFirstLayer = new AnchorPane();
		myParent.getChildren().add(myFirstLayer);
		
		myMap = new GridPane();
		myFirstLayer.getChildren().add(myMap);
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				myMap.add(new Rectangle(50,50), i, j);
			}
		}
		
		myCell = new StackPane();
		myMap.add(myCell, 5, 0);
		
		myCellLayer = new GridPane();
		myOverlayLayer = new GridPane();
		myCell.getChildren().addAll(myCellLayer, myOverlayLayer);
		
		myOverlay = new Rectangle(50, 50);
		myOverlay.setFill(Color.YELLOW);
		myOverlayLayer.getChildren().add(myOverlay);
		
		myScene = new Scene(myRoot);
		stage.setScene(myScene);
		
		myOverlay.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->System.out.println("OVERLAY"));
		myOverlayLayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->System.out.println("OVERLAY-LAYER"));
		myCellLayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->System.out.println("CELL-LAYER"));
		myCell.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->System.out.println("CELL"));
		myMap.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->System.out.println("MAP"));
		myFirstLayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->System.out.println("FIRST LAYER"));
		myParent.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->System.out.println("PARENT"));
		myScene.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->System.out.println("SCENE"));
		
	} 
	
	public static void main(String[] args){
		launch(args);
	}
}
