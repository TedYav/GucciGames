package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveCustomObj;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;


public class TileTab extends AbstractTab {
	private ISaveCustomObj saveCustomObj;
	
	public TileTab(IGuiGaeController controller, ISaveCustomObj saveCustomObj){
		super(controller);
		this.saveCustomObj = saveCustomObj;
		setText("Tiles");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/land.png", "voogasalad_GucciGames/graphics/water.png", "voogasalad_GucciGames/graphics/hurricane.png","voogasalad_GucciGames/graphics/fire.png","voogasalad_GucciGames/graphics/lava.png","voogasalad_GucciGames/graphics/mountain.png","voogasalad_GucciGames/graphics/sand.png");
//		for(MapObjectType maptype : controller.getImmutableTileTypes()){
//			allImagePaths.add(maptype.getMyImagePath());
//		}
		
		addImages();
		addImageHandler();
		addDragDropListener();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	//TODO: create add button, pass saveCustomObj to dialog

}
