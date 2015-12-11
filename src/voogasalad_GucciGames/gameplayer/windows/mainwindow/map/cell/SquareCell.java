package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell;

import javafx.scene.shape.Rectangle;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;

public class SquareCell extends MapCell {

	public SquareCell(GameControllerInterface controller, double myCellSize) {
		super(controller, myCellSize);
	}

	@Override
	protected void initializeOverlayShapes() {
		myOverlay = new Rectangle(mySize, mySize);
		myOverlay.getStyleClass().add("inactivecell");
	}

}
