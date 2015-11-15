package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;


public class UnitTab extends AbstractTab {
	
	public UnitTab(IGuiGaeController controller) {
		super(controller);
		setText("Units");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/fire.png", "voogasalad_GucciGames/graphics/water.png");
		addImages();
		addImageHandler();
		addDragDropListener();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
