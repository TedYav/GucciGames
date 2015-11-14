package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.RealHealthCharacteristic;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerHealthRule extends PlayerConditions {

	private RealHealthCharacteristic myPlayerHealth;

	public PlayerHealthRule(RealHealthCharacteristic player) {
		super(player);
		myPlayerHealth = player;
	}

	@Override
	public List<EndGameConditions> executeRule() {
		List<EndGameConditions> list = new ArrayList<EndGameConditions>();
		if (myPlayerHealth.getCurrentHealth() == 0) {
			list.add(EndGameConditions.LOSE);
		}
		return list;

	}

}
