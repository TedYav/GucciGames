package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.PlayerMapObjectInterface;
import voogasalad_GucciGames.gameEngine.CommunicationParams.ActionToGamePlayerParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.EmptyParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParams.LocationParams;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.gameRules.defaultRules.AttacksPerTurn;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class AttackEvent extends MapObjectEvent{

	public AttackEvent(String actionName) {
		super(actionName);
		getRuleList().add(new AttacksPerTurn());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CommunicationParameters execute(CommunicationParameters params) {
		// TODO Auto-generated method stub
		LocationParams moveParams = (LocationParams) params;
		TargetCoordinateSingle target = moveParams.getNewLocation();
		MapObject calledMe = moveParams.getCalledMe();
		double damage = ((AttackCharacteristic) calledMe.getCharacteristic("AttackCharacteristic")).getDamage();
		
		List<Integer> ids1 = moveParams.getPlayers().getAllIds();
		
		//a hacky way to remove the neutral player
		List<Integer> ids = new ArrayList<Integer>();
		for(int i = 1; i < ids1.size(); i++){
			ids.add(ids1.get(i));
		}
		
		ActionToGamePlayerParameters parameters = new ActionToGamePlayerParameters();
		
		for(Integer id: ids){
			for(MapObject mo: moveParams.getPlayers().getPlayerById(id).getMapObjects()){
				if(mo.getCoordinate().equals(target)){
					System.out.println("damage: " + damage);
	
					HealthCharacteristic hc = (HealthCharacteristic) mo.getCharacteristic("HealthCharacteristic");
					System.out.println("health target:" + hc.getCurrentHealth());
					hc.changeHealth(damage);
					if(hc.getCurrentHealth() < 0){
						moveParams.getPlayers().getActivePlayer(mo.getPlayerID()).getMapObjects().remove(mo);
						parameters.addUnit((PlayerMapObjectInterface) mo);
					}
					break; //can only attack one
				}
			}
		}
		
		((AttackCharacteristic) calledMe.getCharacteristic("AttackCharacteristic")).updateAttackCount();

		
		return parameters; //return the dead units
	}

}
