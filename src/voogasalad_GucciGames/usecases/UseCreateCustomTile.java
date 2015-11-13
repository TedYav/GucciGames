package voogasalad_GucciGames.usecases;

import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.GaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.GAEGui;
import voogasalad_GucciGames.gameAuthoring.model.GAEModel;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;

public class UseCreateCustomTile {
	
	
	public void use() {
		GaeController controller = new GaeController();
		IGAEModel model = new GAEModel((IModelGaeController)controller);
		GAEGui gui = new GAEGui((IGuiGaeController)controller);

		
		/*
		 * In the GUI the author would click to create a new custom tile.
		 * The GUI shows a popup which allows the author to enter parameters
		 * describing the tile. When the author has finished inputting the 
		 * information to describe the new tile, they will click a "create 
		 * tile" button which will trigger a call to createCustomTileType() in
		 * the controller and pass a map with the inputed tile properties.
		 * The createCustomTileType() method makes a call to the model
		 * saving the new custom tile type to the observable list of tile 
		 * types so that it can be displayed by the front end in the tiles 
		 * sidebar. 
		 * 
		 */
		
		
		// This map will actually be passed on the front end call to the
		// controller
		Map<String, String> tileProperties = gui.getMapForCustomTile();
		
		// gui calls
		controller.createCustomTileType(tileProperties);
		
		// controller then calls
		model.createCustomTileType(tileProperties);
		
	}

}
