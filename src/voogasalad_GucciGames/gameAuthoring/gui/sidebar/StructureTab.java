package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;


public class StructureTab extends AbstractTab {

	public StructureTab() {
		super();
		setText("Structures");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/water.png");
		addImages();
		addImageHandler();
		addDragDropListener();
	}
	
	@Override
	public void update() {

	}

}
