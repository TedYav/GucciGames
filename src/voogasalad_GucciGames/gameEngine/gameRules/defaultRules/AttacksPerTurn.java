package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
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

	public AttacksPerTurn() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean executeRules(BasicParameters communicationParams) {
			
		if(communicationParams.getCalledMe().hasCharacteristic("AttackCharacteristic")){
			AttackCharacteristic myAtkCharacteristic = (AttackCharacteristic) (communicationParams.getCalledMe().getCharacteristic("AttackCharacteristic"));
			return myAtkCharacteristic.getMaxAttacks() > myAtkCharacteristic.getCurrentNumberOfAttacks();
		}
		return false;
	}


}
