package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al
 *
 */
public class AttacksPerTurn extends Rules {
private static final String ATTACK_CHARACTERISTIC = "AttackCharacteristic";

	public AttacksPerTurn() {
	}

	@Override
	public Boolean executeRules(BasicParameters communicationParams) {

		if (communicationParams.getCalledMe().hasCharacteristic(ATTACK_CHARACTERISTIC)) {
			AttackCharacteristic myAtkCharacteristic = (AttackCharacteristic) (communicationParams.getCalledMe()
					.getCharacteristic(ATTACK_CHARACTERISTIC));
			return myAtkCharacteristic.getMaxAttacks() > myAtkCharacteristic.getCurrentNumberOfAttacks();
		}
		return false;
	}

}
