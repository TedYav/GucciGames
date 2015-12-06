package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.objectActions.defaultActions.Attack;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class AttackEvent extends Attack {
	public AttackEvent() {
	}

	public AttackEvent(String actionName) {
		super(actionName);
	}

	public AttackEvent(String actionName, List<Rules> rules, List<Outcome> outcomes) {
		super(actionName, rules, outcomes);
	}

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		System.out.println("Attack Action");
		MapObject calledMe = params.getCalledMe();
		double damage = 0.0;
		if (calledMe.containsCharacteristic(ATTACK_CHARACTERISTIC)) {
			damage = ((AttackCharacteristic) calledMe.getCharacteristic(ATTACK_CHARACTERISTIC)).getDamage();

		} else {
			AttackCharacteristic attackChar = new AttackCharacteristic();
			damage = attackChar.getDamage();
		}
		List<Integer> ids = extractAllPlayersExceptNutral(params);

		TargetCoordinateSingle target = params.getNewLocation();
		ChangedParameters parameters = new ChangedParameters();

		for (Integer id : ids) {
			for (MapObject mo : params.getEngine().getPlayers().getPlayerById(id).getMapObjects()) {
				if (mo.getCoordinate().equals(target)) {
					doDamage(params, damage, parameters, mo);
					parameters.addPlayer(id);
					break; // can only attack one
				}
			}
		}

		((AttackCharacteristic) calledMe.getCharacteristic(ATTACK_CHARACTERISTIC)).updateAttackCount();

		return parameters; // return the dead units

	}

	private void doDamage(LocationParameters params, double damage, ChangedParameters parameters, MapObject mo) {
		System.out.println("damage: " + damage);
		HealthCharacteristic hc = (HealthCharacteristic) mo.getCharacteristic(HEALTH_CHARACTERISTIC);
		System.out.println("health target:" + hc.getCurrentHealth());
		hc.changeHealth(damage);
		if (hc.getCurrentHealth() <= 0) {
			GamePlayerPerson playerAttacked = params.getEngine().getPlayers().getActivePlayer(mo.getPlayerID());
			playerAttacked.getMapObjects().remove(mo);
			System.out.println("Map objectName" + mo.getName());
			parameters.addUnit(mo);
			// remove player with 0 objects left
			if (playerAttacked.getMapObjects().size() == 0) {
				params.getEngine().getPlayers().removePlayer(mo.getPlayerID());
				parameters.addPlayer(mo.getPlayerID());
			}

		}
	}

}
