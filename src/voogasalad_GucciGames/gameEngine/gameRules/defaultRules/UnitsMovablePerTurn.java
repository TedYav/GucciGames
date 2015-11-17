package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import java.util.List;

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
	private CommunicationParams myCommunicationParams;

	public UnitsMovablePerTurn(CommunicationParams communicationParams, List<Object> ruleArgs) {
		super(communicationParams, ruleArgs);
		targetValue = (int) ruleArgs.get(0);
		myCommunicationParams = communicationParams;
	}

	@Override
	public Boolean executeRules(int playerID) {
		int unitsMovedCounter = myCommunicationParams.getPlayers().getPlayerById(playerID).getUnitsMoved();
		if (unitsMovedCounter < targetValue) {
			return true;
		} else
			return false;
	}

}
