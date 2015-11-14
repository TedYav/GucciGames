
package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.List;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.RealHealthCharacteristic;

/**
 *
 * @author Sally Al
 *
 */

//make real and null
public abstract class PlayerConditions extends GameConditions {

	public PlayerConditions(RealHealthCharacteristic playerhealth) {

	}
	protected  abstract List<EndGameConditions> executeRule();

}
