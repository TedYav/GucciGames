package voogasalad_GucciGames.gameEngine.CommunicationParams;

import java.util.List;

import voogasalad_GucciGames.gameEngine.GameMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.ATargetCoordinate;

public class WhereToAttackParams extends CommunicationParams{

	private List<ATargetCoordinate> myPossibilities;
	
	public WhereToAttackParams(CommunicationParams params, List<ATargetCoordinate> pos) {
		super(params);
		// TODO Auto-generated constructor stub
		this.myPossibilities = pos;
	}
	
	public List<ATargetCoordinate> getPossibilites(){
		return this.myPossibilities;
	}
	 

}
