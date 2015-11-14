
package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.List;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.RealHealthCharacteristic;

/**
 *
 * @author Sally Al
 *
 */

//make real and null
//this class replaces playergamerule.java
public abstract class PlayerRules extends GameRule {

	public PlayerRules(RealHealthCharacteristic playerhealth) {

	}
	protected  abstract List<EndGameConditions> executeRule();

}
