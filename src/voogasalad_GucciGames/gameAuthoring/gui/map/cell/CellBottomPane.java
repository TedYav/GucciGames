package voogasalad_GucciGames.gameAuthoring.gui.map.cell;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class CellBottomPane extends Pane {
	
	private MapObject myTile;
	private ImageView myTileImage;
	private IGuiGaeController myController;
	
	public CellBottomPane(IGuiGaeController controller) {
		myController = controller;
		myTileImage = new ImageView();
		myTileImage.fitWidthProperty().bind(widthProperty());
		myTileImage.fitHeightProperty().bind(heightProperty());
		getChildren().add(myTileImage);
	}
	
	public boolean add(MapObject tile){
		myTile = tile;
		myTileImage.setImage(myController.requestImage(tile.getImageURI()));
		//TODO: add in backend
		return true;
	}
	
	public boolean remove(){
		myTile = null;
		myTileImage.setImage(null);
		//TODO: remove in backend
		return true;
	}
}
