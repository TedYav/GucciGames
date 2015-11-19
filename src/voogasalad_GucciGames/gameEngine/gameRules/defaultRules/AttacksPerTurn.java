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
private int objectAllowedAttacks;
private String objType;



	public AttacksPerTurn(RuleParams myParams, BasicParameters params) {
		super(myParams, params);
		objectAllowedAttacks=(int) myParams.getArgs().get(object);
		objType= myParams.getunitType().get(0);
		}

	@Override
	public Boolean executeRules(BasicParameters communicationParams) {
		if(objType.equals(communicationParams.getCalledMe().getName())){


		}
		return null;
	}

}
