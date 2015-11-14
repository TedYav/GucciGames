package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;


public class UnitTab extends AbstractTab {
	
	public UnitTab() {
		super();
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
