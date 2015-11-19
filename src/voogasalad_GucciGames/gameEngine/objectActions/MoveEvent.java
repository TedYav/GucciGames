package voogasalad_GucciGames.gameEngine.objectActions;

import voogasalad_GucciGames.gameEngine.CommunicationParams.ActionToGamePlayerParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.LocationParams;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

//Test at testing.TestActions.java

public class MoveEvent extends MapObjectEvent{


	public MoveEvent(String actionName) {
		super(actionName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CommunicationParameters execute(CommunicationParameters params) {
		// TODO Auto-generated method stub
		LocationParams moveParams = (LocationParams) params;
		TargetCoordinateSingle target = moveParams.getNewLocation();
		MapObject moving = moveParams.getCalledMe();
		moving.setCoordinate(target);
		moveParams.getMovePerson().updateMoves();
		
		ActionToGamePlayerParameters myParameters  = new ActionToGamePlayerParameters();
		
		myParameters.addUnit(moving);
		
		return myParameters;
	}

}
