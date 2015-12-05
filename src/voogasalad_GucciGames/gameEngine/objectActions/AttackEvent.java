package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;
import voogasalad_GucciGames.gameEngine.gameRules.defaultRules.AttacksPerTurn;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class AttackEvent extends MapObjectEvent {

	public AttackEvent(String actionName, List<Rules> rules, List<Outcome> outcomes) {
		super(actionName, rules, outcomes);
		getRuleList().add(new AttacksPerTurn());
	}

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		System.out.println("Attack Action");
		TargetCoordinateSingle target = params.getNewLocation();
		MapObject calledMe = params.getCalledMe();
		double damage = ((AttackCharacteristic) calledMe.getCharacteristic("AttackCharacteristic")).getDamage();

		List<Integer> ids1 = params.getEngine().getPlayers().getAllIds();

		// a hacky way to remove the neutral player
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 1; i < ids1.size(); i++) {
			ids.add(ids1.get(i));
		}

		ChangedParameters parameters = new ChangedParameters();

		for (Integer id : ids) {
			for (MapObject mo : params.getEngine().getPlayers().getPlayerById(id).getMapObjects()) {
				if (mo.getCoordinate().equals(target)) {
					System.out.println("damage: " + damage);

					HealthCharacteristic hc = (HealthCharacteristic) mo.getCharacteristic("HealthCharacteristic");
					System.out.println("health target:" + hc.getCurrentHealth());
					hc.changeHealth(damage);
					if (hc.getCurrentHealth() <= 0) {
						GamePlayerPerson playerAttacked = params.getEngine().getPlayers()
								.getActivePlayer(mo.getPlayerID());
						playerAttacked.getMapObjects().remove(mo);
						System.out.println("Map objectName" + mo.getName());
						parameters.addUnit(mo);
						// remove player with 0 objects left
						if (playerAttacked.getMapObjects().size() == 0) {
							params.getEngine().getPlayers().removePlayer(mo.getPlayerID());
							parameters.addPlayer(mo.getPlayerID());
						}

					}
					break; // can only attack one
				}
			}
		}

		((AttackCharacteristic) calledMe.getCharacteristic("AttackCharacteristic")).updateAttackCount();

		return parameters; // return the dead units

	}

	@Override
	protected GridCoordinateParameters executeRequest(BasicParameters params) {
		// TODO Auto-generated method stub
		System.out.println("Attack Request");
		AllPlayers players = params.getEngine().getPlayers();

		TargetCoordinateMultiple result = new TargetCoordinateMultiple();

		// getting the range
		MapObject calledMe = params.getCalledMe();
		AttackCharacteristic ac = (AttackCharacteristic) calledMe.getCharacteristic("AttackCharacteristic");
		double range = ac.getRange();

		TargetCoordinateSingle caller = (TargetCoordinateSingle) calledMe.getCoordinate();

		players.getAllIds().stream().forEach(id -> {
			players.getPlayerById(id).getMapObjects().stream().forEach(mo -> {
				if (mo.hasCharacteristic("HealthCharacteristic")) {
					TargetCoordinateSingle single = (TargetCoordinateSingle) mo.getCoordinate();
					double dx = Math.abs(single.getCenterX() - caller.getCenterX());
					double dy = Math.abs(single.getCenterY() - caller.getCenterY());
					if (checkNeighborhood(dx, dy, range)) {
						result.addTargetCoordinateSingle(single);
					}
				}
			});
		});

		return new GridCoordinateParameters(result);
	}

}
