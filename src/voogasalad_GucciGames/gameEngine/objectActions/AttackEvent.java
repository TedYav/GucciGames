package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.LocationParams;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class AttackEvent extends MapObjectEvent{

	public AttackEvent(String actionName) {
		super(actionName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CommunicationParameters execute(CommunicationParameters params) {
		// TODO Auto-generated method stub
		LocationParams moveParams = (LocationParams) params;
		TargetCoordinateSingle target = moveParams.getNewLocation();
		MapObject calledMe = moveParams.getCalledMe();
		double damage = ((AttackCharacteristic) calledMe.getObjectType().getCharacteristic("AttackCharacteristic")).getDamage();
		
		List<Integer> ids = moveParams.getPlayers().getAllIds();
		
		for(Integer id: ids){
			for(MapObject mo: moveParams.getPlayers().getPlayerById(id).getMapObjects()){
				if(mo.getCoordinate().equals(target)){
					HealthCharacteristic hc = (HealthCharacteristic) mo.getObjectType().getCharacteristic("HealthCharacteristic");
					hc.changeHealth(damage);
					break; //can only attack one
				}
			}
		}
		return new EmptyParameters();
	}

}
