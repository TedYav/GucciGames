package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveCustomObj;


public class UnitTab extends AbstractTab {
	private ISaveCustomObj saveCustomObj;
	
	public UnitTab(IGuiGaeController controller, ISaveCustomObj saveCustomObj) {
		super(controller);
		this.saveCustomObj = saveCustomObj;
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
