package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParams;
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
	protected CommunicationParams execute(CommunicationParams params) {
		// TODO Auto-generated method stub
		LocationParams moveParams = (LocationParams) params;
		TargetCoordinateSingle target = moveParams.getNewLocation();
		MapObject calledMe = params.getCalledMe();
		double damage = ((AttackCharacteristic) calledMe.getObjectType().getCharacteristic("AttackCharacteristic")).getDamage();
		
		List<Integer> ids = params.getPlayers().getAllIds();
		
		for(Integer id: ids){
			for(MapObject mo: params.getPlayers().getPlayerById(id).getMapObjects()){
				if(mo.getCoordinate().equals(target)){
					HealthCharacteristic hc = (HealthCharacteristic) mo.getObjectType().getCharacteristic("HealthCharacteristic");
					hc.changeHealth(damage);
					break; //can only attack one
				}
			}
		}
		return new EmptyParams();
	}

}
