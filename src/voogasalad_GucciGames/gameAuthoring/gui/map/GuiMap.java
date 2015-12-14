// This entire file is part of my masterpiece.
// Mike Ma (ym67)
package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;

/** Constructs a scene with a pannable Map background. */
public class GuiMap extends Pane implements IGuiMap {
	private final GridViewer myGridViewer;

	public GuiMap(AGuiGaeController controller, int id) {
		myGridViewer = new GridViewer(controller, id);
		getChildren().add(myGridViewer);
		new GridEventHandler(this, myGridViewer);
	}

	@Override
	public void initGrid(int width, int height) {
		myGridViewer.getGrid().initGrid(width, height);
	}

	@Override
	public void setBackground(Image background) {
		myGridViewer.getGrid().setBackground(background);
	}

}