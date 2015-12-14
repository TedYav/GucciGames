// This entire file is part of my masterpiece.
// Joy Patel
package voogasalad_GucciGames.gameEngine.objectActions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.GridCoordinateParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.LocationParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateMultiple;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

//Test at testing.TestActions.java

public class MoveEvent extends MapObjectEvent {

	private static final String MOVABLE_CHARACTERISTIC = "MovableCharacteristic";

	public MoveEvent() {
	}

	public MoveEvent(String actionName) {
		super(actionName);
	}

	public MoveEvent(String actionName, List<Rules> rules, List<Outcome> outcomes) {
		super(actionName, rules, outcomes);
	}

	@Override
	protected ChangedParameters executeAction(LocationParameters params) {
		System.out.println("Move Action");
		TargetCoordinateSingle target = params.getNewLocation();
		MapObject moving = params.getCalledMe();
		moving.setCoordinate(target);
		AllPlayers players = params.getEngine().getPlayers();
		GamePlayerPerson player = players.getActivePlayer(params.getCalledMe().getPlayerID());
		player.getMovable().updateMoves();
		ChangedParameters myParameters = new ChangedParameters();
		myParameters.addUnit(moving);

		return myParameters;
	}

	@Override
	protected GridCoordinateParameters executeRequest(BasicParameters params) {
		System.out.println("Move Request");
		AllPlayers players = params.getEngine().getPlayers();
		TargetCoordinateMultiple result = new TargetCoordinateMultiple();
		MapObject calledMe = params.getCalledMe();

		// getting the range
		MovableCharacteristic mc;
		if (calledMe.containsCharacteristic(MOVABLE_CHARACTERISTIC)) {
			mc = (MovableCharacteristic) calledMe.getCharacteristic(MOVABLE_CHARACTERISTIC);
		} else {
			mc = new MovableCharacteristic();
		}
		double range = mc.getMoveRange();
		// going through neutral player
		TargetCoordinateSingle caller = (TargetCoordinateSingle) calledMe.getCoordinate();
		Set<TargetCoordinateSingle> otherCoor = new HashSet<>();

		players.getNonNeutralMapObjects().stream().forEach(obj -> {
			otherCoor.add((TargetCoordinateSingle) obj.getCoordinate());
		});
		players.getActivePlayer(-1).getMapObjects().stream().forEach(mo -> {
			// Fix this
			if (mo.hasCharacteristic("TileCharacteristic") || mo.getName().equals("TileCharacteristic")) {
				TargetCoordinateSingle single = (TargetCoordinateSingle) mo.getCoordinate();
				double dx = Math.abs(single.getCenterX() - caller.getCenterX());
				double dy = Math.abs(single.getCenterY() - caller.getCenterY());

				if (checkNeighborhood(dx, dy, range) && !otherCoor.contains(mo.getCoordinate())) {
					result.addTargetCoordinateSingle(mo.getCoordinate());
				}
			}
		});
		return new GridCoordinateParameters(result);
	}

}
