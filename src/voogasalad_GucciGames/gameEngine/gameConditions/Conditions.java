
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.List;

import voogasalad_GucciGames.gameEngine.CommunicationParams.BasicParameters;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;

/**
 *
 * @author Sally Al
 *
 */
public abstract class Conditions {

	public Conditions(List<GamePlayerPerson> players, BasicParameters params) {
	}

	public abstract void execute(BasicParameters params);

}
