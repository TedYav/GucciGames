package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.MapObjectBasicType;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.PlayerMapObjectInterface;

public abstract class MapCell implements MapCellInterface {

	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.gameplayer.config.components.MapCell");
	
	// TODO: factor this into two or three classes eventually	
	private StackPane myParent;
	
	private StackPane myObjectLayer;
	protected Map<Integer, GridPane> myLayerMap;
	
	private StackPane myOverlayLayer;
	protected Shape mySelectedOverlay;
	protected Shape myHighlightOverlay;
	protected Shape myFogOverlay;
	protected Shape myHoverOverlay;
	
	private GameControllerInterface myController;
	
	private double mySize;
		
	private Map<Integer, List<PlayerMapObjectInterface>> myObjects;
	
	public MapCell(GameControllerInterface controller, double myCellSize){
		initializeVariables(controller, myCellSize);
		initializePanes();
		initializeOverlays();
	}
	
	// dependent on shape
	protected abstract void initializeOverlays();

	private void initializeVariables(GameControllerInterface controller, double myCellSize){
		myController = controller;
		mySize = myCellSize;
		myObjects = new HashMap<>();
		myLayerMap = new TreeMap<>();
	}
		
	private void initializePanes() {
		myParent = new StackPane();
		myObjectLayer = new StackPane();
		myOverlayLayer = new StackPane();
		myParent.getChildren().addAll(myObjectLayer, myOverlayLayer);
		myParent.setMaxSize(mySize, mySize);
		
	}

	public Parent getParent(){
		return myParent;
	}
	
	public void addObject(PlayerMapObjectInterface object){
		System.out.println(object);
		if(!myObjects.containsKey(object.getLayer())){
			myObjects.put(object.getLayer(), new ArrayList<PlayerMapObjectInterface>());
		}
		myObjects.get(object.getLayer()).add(object);
		redrawLayer(object.getLayer());
	}
	
	public void removeObject(PlayerMapObjectInterface object){
		myObjects.get(object.getLayer()).remove(object);
		redrawLayer(object.getLayer());
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
		myObjects.keySet().forEach((i) -> redrawLayer(i));
	}
	
	private void redrawLayer(int layer){
		System.out.println("REDRAWING LAYER " + layer);
		makeLayers(layer);
		int count = myObjects.get(layer).size();
		myLayerMap.get(layer).getChildren().removeAll();
		if(count > 0){
			double countPerRow = Math.ceil(Math.sqrt(count));
			for(int i=0, total=0; i<countPerRow; i++){
				for(int j=0; j<countPerRow; j++, total++){
					if(total==count) break;
					myLayerMap.get(layer).getChildren().add(renderImage(myObjects.get(layer).get(total), (mySize/countPerRow)));
				}
			}
		}
	}

	private ImageView renderImage(PlayerMapObjectInterface object, double size) {
		System.out.println(myController);
		ImageView image = new ImageView(myController.getMap().requestImage(object.getImageURI()));
		image.setFitWidth(size);
		image.setFitHeight(size);
		return image;
	}

	private void makeLayers(int layer) {
		if(!myLayerMap.containsKey(layer)){
			for(int i=0; i<=layer; i++){
				if(myLayerMap.containsKey(i))
					continue;
				GridPane g = new GridPane();
				myLayerMap.put(i, g);
				System.out.println("ADDING LAYER " + i);
				myObjectLayer.getChildren().add(g);
			}
		}
	}
	
	
	public void addTemporaryOverlay(Node overlay, double duration){
		
	}
	
}
