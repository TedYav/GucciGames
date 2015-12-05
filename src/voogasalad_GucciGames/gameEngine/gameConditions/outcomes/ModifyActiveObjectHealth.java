package voogasalad_GucciGames.gameEngine.gameConditions.outcomes;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.ChangedParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;

/**
 *
 * @author Sally Al
 *
 */
public class ModifyActiveObjectHealth extends Outcome {
	private static final String HEALTH = "health";
	private int deltaHealth = 0;

	public ModifyActiveObjectHealth(List<Conditions> conditions, Map<String, Object> outcomeParams) {
		super(conditions, outcomeParams);
		deltaHealth = (int) outcomeParams.get(HEALTH);

	}

	@Override
	ChangedParameters applyOutcome(BasicParameters params, ChangedParameters changedParams, int i) {
		MapObject obj = params.getCalledMe();
		HealthCharacteristic objChar = (HealthCharacteristic) obj.getCharacteristic(HEALTH);
		objChar.changeHealth(deltaHealth);
		changedParams.addUnit(obj);
		return changedParams;
	}

}
