package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;

public class WhereToParams extends CommunicationParameters{
	
	private TargetCoordinateMultiple myTargetCoordinateMultiple;
	
	public WhereToParams(TargetCoordinateMultiple target) {
		super();
		// TODO Auto-generated constructor stub
		this.myTargetCoordinateMultiple = target;
	}
	
	public TargetCoordinateMultiple getTargetCoordinateMultiple(){
		return this.myTargetCoordinateMultiple;
	}

}
