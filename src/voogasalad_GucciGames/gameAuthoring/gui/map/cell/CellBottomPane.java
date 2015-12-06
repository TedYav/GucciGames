package voogasalad_GucciGames.gameAuthoring.gui.map.cell;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.model.DisplayMapObject;

public class CellBottomPane extends Pane {

	private DisplayMapObject myTile;
	private ImageView myTileImage;
	private IGuiGaeController myController;

	public CellBottomPane(IGuiGaeController controller) {
		myController = controller;
	}

	public void add(DisplayMapObject tile) {
		remove();
		myTile = tile;
		myTileImage = myController.getMapObjectImage(tile);
		myTileImage.fitWidthProperty().bind(widthProperty());
		myTileImage.fitHeightProperty().bind(heightProperty());
		getChildren().add(myTileImage);
	}

	public boolean remove() {
		if (myTile != null) {
			myTile = null;
			myTileImage.fitWidthProperty().unbind();
			myTileImage.fitHeightProperty().unbind();
			getChildren().remove(myTileImage);
			myTileImage = null;
			return true;
		} else {
			return false;
		}

	}

	public DisplayMapObject getTile() {
		return myTile;
	}
}
