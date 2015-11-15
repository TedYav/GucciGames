package voogasalad_GucciGames.gameEngine.mapObject;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;

public interface IMapObjectActionTarget {

	
	public ATargetCoordinate coordinatesToAct(CommunicationParams params);
	
	
}
