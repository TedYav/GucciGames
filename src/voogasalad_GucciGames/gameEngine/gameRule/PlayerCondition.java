
package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.List;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.RealHealthCharacteristic;

/**
 *
 * This condition only checks for one player (most likely user) instead of the
 * whole map (an instance of this would be to check if a player has achieved X
 * money. May be deprecated since everything one can do with a PlayerCondition
 * can be more generally implemented with a GloablGameCondition.
 *
 *
 *
 * @author Sally Al
 * @author Efe Aras
 */

// make real and null
// this class replaces playergamerule.java
public abstract class PlayerCondition implements GameCondition {


	public PlayerCondition(RealHealthCharacteristic playerhealth) {

	}
	protected  abstract List<EndGameConditions> executeRule();

}
