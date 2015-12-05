package voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public class CheckSteppingOn extends Conditions {
	private static final String TARGET_TYPE = "targetType";
	private String myType;

	public CheckSteppingOn(Map<String, Object> condParams) {
		super(condParams);
		if (condParams.containsKey(TARGET_TYPE)) {
			myType = (String) condParams.get(TARGET_TYPE);
		}
	}

	@Override
	public Boolean execute(BasicParameters params, GamePlayerPerson player) {
		return null;
	}

}
