package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import javafx.stage.Stage;


public class StructureTab extends AbstractTab {

	public StructureTab(Stage stage) {
		super(stage);
		setText("Structures");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/water.jpg");
		addImages();
		addImageHandler();
		addDragDropListener();
	}
	
	@Override
	public void update() {

	}

}
