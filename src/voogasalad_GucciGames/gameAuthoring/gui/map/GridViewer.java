// This entire file is part of my masterpiece.
// Mike Ma (ym67)
package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ScrollPane;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

class GridViewer extends ScrollPane {
	private final IntegerProperty myGridSize = new SimpleIntegerProperty(20); // default size
	private final AGuiGaeController myController;
	private final DoubleProperty myCellSize = new SimpleDoubleProperty();
	private final Grid myGrid;

	public GridViewer(AGuiGaeController controller, int id) {
		myController = controller;
		initDimensions();
		myGrid = new Grid(myCellSize, myController, id);
		setContent(myGrid);
	}

	private void initDimensions() {
		viewportBoundsProperty().addListener((ch, oV, nV) -> myCellSize.set(nV.getWidth() / myGridSize.get()));
		myGridSize.addListener((ch, oV, nV) -> myCellSize.set(viewportBoundsProperty().get().getWidth() / myGridSize.get()));
		setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		setPannable(true);
		// center the scroll contents.
		setHvalue(getHmin() + (getHmax() - getHmin()) / 2);
		setVvalue(getVmin() + (getVmax() - getVmin()) / 2);

		prefViewportWidthProperty().bind(widthProperty());
		prefViewportHeightProperty().bind(heightProperty());
	}

	protected Grid getGrid() {
		return myGrid;
	}

	protected DoubleProperty getCellSize() {
		return myCellSize;
	}

	protected IntegerProperty getGridSize() {
		return myGridSize;
	}
}
