package voogasalad_GucciGames.usecases;

import voogasalad_GucciGames.gameAuthoring.GaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.GameAuthoringEnvironmentGUI;
import voogasalad_GucciGames.gameAuthoring.model.GAEModel;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;

public class UseSaveGame {
	
	
	public void use() {

		GaeController controller = new GaeController();
		IGAEModel model = new GAEModel((IModelGaeController)controller);
		GameAuthoringEnvironmentGUI gui = new GameAuthoringEnvironmentGUI((IGuiGaeController)controller);
		
		/*
		 * 
		 * 
		 * Model has instance of XMLGameData which does saving
		 * 
		 * 
		 */
		
		
		//controller.addComponent(unit);
		
		
		//model.addComponent(unit);
	}

}
