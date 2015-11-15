package voogasalad_GucciGames.usecases;

import java.util.HashMap;
import java.util.Map;
import voogasalad_GucciGames.gameAuthoring.GaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.model.GAEModel;
import voogasalad_GucciGames.gameAuthoring.model.IGAEModel;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

public class UseDragAndDropUnit {
	
	
	public void use() {

/*		GaeController controller = new GaeController();
		IGAEModel model = new GAEModel((IModelGaeController)controller);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> feature_gae_frontend_dialog
		GameAuthoringEnvironmentGUI gui = new GameAuthoringEnvironmentGUI((IGuiGaeController)controller);
	*/	
		/*
		 * When a user drags and drops a unit or tile onto the map
		 * a map object is initialized based on the type of the unit
		 * or tile. A map object contains both the type of the unit 
		 * or tile but also contains its coordinate on the map. 
		 * The initialized object is passed to the controller which 
		 * then passes it in a call to the model where it is stored
		 * in the back end.
		 *  
		 */
		
		
		// This unit would be initialized by the front end
		// then passed through the call to the controller
		Map<String,String> unit = new HashMap<String,String>();
/*		MapObject unit = new MapObject();
>>>>>>> 56b04a76cc49420d6a705704811b1df68a756cb0
		
		controller.addComponent(unit);
		model.addComponent(unit); */
	}
}
