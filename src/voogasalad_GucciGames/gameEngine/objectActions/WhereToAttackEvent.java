package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.GridCoordinateParameters;
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
	protected CommunicationParameters execute(CommunicationParameters params) {
		// TODO Auto-generated method stub
		BasicParameters basic = (BasicParameters) params;
		AllPlayers players = basic.getPlayers();
		
		TargetCoordinateMultiple result = new TargetCoordinateMultiple();

		// getting the range
		MapObject calledMe = basic.getCalledMe();
		AttackCharacteristic ac = (AttackCharacteristic) calledMe.getCharacteristic("AttackCharacteristic");
		double range = ac.getRange();
		
		TargetCoordinateSingle caller = (TargetCoordinateSingle) calledMe.getCoordinate();
		
		players.getAllIds().stream().forEach(id ->{
			players.getPlayerById(id).getMapObjects().stream().forEach(mo -> {
				if(mo.hasCharacteristic("HealthCharacteristic")){
					TargetCoordinateSingle single = (TargetCoordinateSingle) mo.getCoordinate();
					double dx = Math.abs(single.getCenterX()-caller.getCenterX());
					double dy = Math.abs(single.getCenterY()-caller.getCenterY());
					if(checkNeighborhood(dx,dy,range)){
						result.addTargetCoordinateSingle(single);
					}
				}
			});
		});
		
		return new GridCoordinateParameters(result);
	}

}
