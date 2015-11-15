package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;


public class StructureTab extends AbstractTab {

	public StructureTab(IGuiGaeController controller) {
		super(controller);
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
