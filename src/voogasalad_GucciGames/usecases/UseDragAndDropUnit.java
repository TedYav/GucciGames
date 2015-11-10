package voogasalad_GucciGames.usecases;

import voogasalad_GucciGames.gameAuthoring.GaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.GameAuthoringEnvironmentGUI;
import voogasalad_GucciGames.gameAuthoring.model.GAEModel;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class UseDragAndDropUnit {
	
	
	public void use() {

		GaeController controller = new GaeController();
		IGAEModel model = new GAEModel((IModelGaeController)controller);
		GameAuthoringEnvironmentGUI gui = new GameAuthoringEnvironmentGUI((IGuiGaeController)controller);
		
		
		
		
		MapObject unit = new MapObject(); 
		
		controller.addComponent(unit);
		
		
		model.addComponent(unit);
	}
}
