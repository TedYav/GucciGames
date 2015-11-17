package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.WhereToParams;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class WhereToAttackEvent extends MapObjectEvent{

	public WhereToAttackEvent(String actionName) {
		super(actionName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CommunicationParams execute(CommunicationParams params) {
		// TODO Auto-generated method stub
		AllPlayers players = params.getPlayers();
		List<Integer> ids = players.getAllIds();
		
		TargetCoordinateMultiple result = new TargetCoordinateMultiple();

		// getting the range
		MapObject calledMe = params.getCalledMe();
		AttackCharacteristic ac = (AttackCharacteristic) calledMe.getObjectType().getCharacteristic("AttackCharacteristic");
		double range = ac.getRange();
		
		TargetCoordinateSingle caller = (TargetCoordinateSingle) calledMe.getCoordinate();
		for(int i = 0; i < ids.size(); i++){
			GamePlayerPerson other = players.getPlayerById(ids.get(i));
			for(MapObject mo: other.getMapObjects()){
				if(mo.getObjectType().hasCharacteristic("HealthCharacteristic")){
					TargetCoordinateSingle single = (TargetCoordinateSingle) mo.getCoordinate();
					double delta = Math.abs(single.getCenterX()-caller.getCenterX())+Math.abs(single.getCenterY()-caller.getCenterY());
					if(delta <= range){
						result.addCoordinate(single);
					}
				}
			}
			
		}
		
		return new WhereToParams(params,result);
	}

}
