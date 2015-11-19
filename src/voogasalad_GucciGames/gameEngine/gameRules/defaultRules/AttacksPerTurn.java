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
private String object;
private String anotherObject;
private int objectAllowedAttacks;
private int anotherObjectAllowedAttacks;

	public AttacksPerTurn(RuleParams myParams, BasicParameters params) {
		super(myParams, params);
		objectAllowedAttacks=(int) myParams.getArgs().get(object);
		anotherObjectAllowedAttacks=(int) myParams.getArgs().get(anotherObject);
		}

	@Override
	public Boolean executeRules(BasicParameters communicationParams, int playerID) {
		return null;
	}

}
