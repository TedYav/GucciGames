package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveCustomObj;


public class StructureTab extends AbstractTab {
	private ISaveCustomObj saveCustomObj;

	public StructureTab(IGuiGaeController controller, ISaveCustomObj saveCustomObj) {
		super(controller);
		this.saveCustomObj = saveCustomObj;
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
