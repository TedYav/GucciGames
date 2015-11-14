package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;


public class TileTab extends AbstractTab {
	
	public TileTab(){
		super();
		setText("Tiles");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/land.png", "voogasalad_GucciGames/graphics/water.png", "voogasalad_GucciGames/graphics/hurricane.png","voogasalad_GucciGames/graphics/fire.png","voogasalad_GucciGames/graphics/lava.png","voogasalad_GucciGames/graphics/mountain.png","voogasalad_GucciGames/graphics/sand.png");
		addImages();
		addImageHandler();
		addDragDropListener();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
