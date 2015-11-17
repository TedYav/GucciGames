package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;

public class WhereToParams extends CommunicationParams{
	
	private TargetCoordinateMultiple myTargetCoordinateMultiple;
	
	public WhereToParams(CommunicationParams params, TargetCoordinateMultiple target) {
		super(params);
		// TODO Auto-generated constructor stub
		this.myTargetCoordinateMultiple = target;
	}
	
	public TargetCoordinateMultiple getTargetCoordinateMultiple(){
		return this.myTargetCoordinateMultiple;
	}

}
