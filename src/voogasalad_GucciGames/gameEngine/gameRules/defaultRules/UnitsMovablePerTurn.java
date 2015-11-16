package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import java.util.Map;

import voogasalad_GucciGames.gameEngine.CommunicationParams.CommunicationParams;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al
 *
 */
public class UnitsMovablePerTurn extends Rules {
	private int targetValue;
	private String target;
	private CommunicationParams myBigMap;

	public UnitsMovablePerTurn(CommunicationParams theBigMap, Map<String, Object> ruleArgs) {
		super(theBigMap, ruleArgs);
		targetValue = (int) ruleArgs.get(target);
		myBigMap = theBigMap;
	}

	@Override
	public Boolean executeRules(int playerID) {
		int unitsMovedCounter = myBigMap.getPlayers().getActivePlayer(playerID).getMyPlayerId();
		if (unitsMovedCounter < targetValue) {
			return true;
		} else
			return false;
	}

}
