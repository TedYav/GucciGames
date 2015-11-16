package voogasalad_GucciGames.gameEngine.mapObject;
//@Sally, edited this class for use case #2
import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;

public interface IMapObjectAction{
	
	public CommunicationParams action(CommunicationParams communication, ATargetCoordinate targetCoords);
	
}
