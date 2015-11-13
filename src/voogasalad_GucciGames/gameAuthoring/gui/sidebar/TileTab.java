package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import javafx.stage.Stage;

public class TileTab extends AbstractTab {
	
	public TileTab(Stage stage){
		super(stage);
		setText("Tiles");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/fire.png", "voogasalad_GucciGames/graphics/water.jpg");
		addImages();
		addImageHandler();
		addDragDropListener();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
