package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

/**
 *
 * @author Sally Al
 *
 */
public class ModifyActiveObjectHealth extends Outcome {
	private static final String HEALTH = "HealthCharacteristic";
	private int deltaHealth = 0;

	public ModifyActiveObjectHealth( String affectedPlayers, int value) {
		super(affectedPlayers);
		deltaHealth = value;
	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params, ChangedParameters changedParams, int i) {
		MapObject obj = params.getCalledMe();
		if (obj.containsCharacteristic(HEALTH)) {
			HealthCharacteristic objChar = (HealthCharacteristic) obj.getCharacteristic(HEALTH);
			objChar.changeHealth(deltaHealth);
			changedParams.addUnit(obj);
		}
		return changedParams;
	}

}
