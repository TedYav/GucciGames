package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.gameRules.RuleParams;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;

/**
 *
 * @author Sally Al
 *
 */
public class AttacksPerTurn extends Rules{

	public AttacksPerTurn(RuleParams myParams, BasicParameters params) {
		super(myParams, params);
	}

	@Override
	public Boolean executeRules(BasicParameters communicationParams, int playerID) {
		return null;
	}

}
