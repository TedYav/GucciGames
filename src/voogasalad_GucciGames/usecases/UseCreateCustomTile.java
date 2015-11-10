package voogasalad_GucciGames.usecases;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.GaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.GameAuthoringEnvironmentGUI;
import voogasalad_GucciGames.gameAuthoring.model.GAEModel;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;

public class UseCreateCustomTile {
	
	
	public void use() {
		GaeController controller = new GaeController();
		IGAEModel model = new GAEModel((IModelGaeController)controller);
		GameAuthoringEnvironmentGUI gui = new GameAuthoringEnvironmentGUI((IGuiGaeController)controller);
		// Author click to create new custom tile
		// GUI shows popup and Author enters parameters
		// Author then clicks to create tile
		// Map of properties is sent 
		
		// Pretend that the front end called a method and passed us this map 
		Map<String, String> tileProperties = gui.getMapForCustomTile();
		gui.getController().createCustomTileType(tileProperties);

		
	}
	

}
