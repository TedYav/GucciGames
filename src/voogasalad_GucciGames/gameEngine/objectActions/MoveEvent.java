package voogasalad_GucciGames.gameEngine.objectActions;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.MoveParams;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

//Test at testing.TestActions.java

public class MoveEvent extends MapObjectEvent{


	public MoveEvent(String actionName) {
		super(actionName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CommunicationParams execute(CommunicationParams params) {
		// TODO Auto-generated method stub
		MoveParams moveParams = (MoveParams) params;
		TargetCoordinateSingle target = moveParams.getNewLocation();
		MapObject moving = params.getCalledMe();
		moving.setCoordinate(target);
		return new EmptyParams();
	}

}
