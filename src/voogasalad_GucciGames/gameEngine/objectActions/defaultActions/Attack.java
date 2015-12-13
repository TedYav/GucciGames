package voogasalad_GucciGames.gameEngine.objectActions.defaultActions;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEvent;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Attack extends MapObjectEvent {
	protected static final String ATTACK_CHARACTERISTIC = "AttackCharacteristic";
	protected static final String HEALTH_CHARACTERISTIC = "HealthCharacteristic";

	public Attack() {

	}

	public Attack(String actionName) {
		super(actionName);
	}

	public Attack(String actionName, List<Rules> rules, List<Outcome> outcomes) {
		super(actionName, rules, outcomes);
	}

	protected List<Integer> extractAllPlayersExceptNutral(LocationParameters params) {
		List<Integer> ids1 = params.getEngine().getPlayers().getAllExistingIds();

		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 1; i < ids1.size(); i++) {
			ids.add(ids1.get(i));
		}
		return ids;
	}

	@Override
	protected GridCoordinateParameters executeRequest(BasicParameters params) {
		System.out.println("Attack Request");
		AllPlayers players = params.getEngine().getPlayers();

		TargetCoordinateMultiple result = new TargetCoordinateMultiple();

		// getting the range
		MapObject calledMe = params.getCalledMe();
		AttackCharacteristic ac;
		System.out.println(calledMe.containsCharacteristic(ATTACK_CHARACTERISTIC));
		if (calledMe.containsCharacteristic(ATTACK_CHARACTERISTIC)) {
			ac = (AttackCharacteristic) calledMe.getCharacteristic(ATTACK_CHARACTERISTIC);

		} else {
			ac = new AttackCharacteristic();
		}
		double range = ac.getAttackRange();

		TargetCoordinateSingle caller = (TargetCoordinateSingle) calledMe.getCoordinate();

		players.getAllExistingIds().stream().forEach(id -> {
			players.getPlayerById(id).getMapObjects().stream().forEach(mo -> {
				if (mo.hasCharacteristic(HEALTH_CHARACTERISTIC)) {
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
