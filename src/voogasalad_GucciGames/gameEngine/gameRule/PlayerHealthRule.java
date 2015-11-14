package voogasalad_GucciGames.gameEngine.gameRule;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameEngine.defaultCharacteristics.RealHealthCharacteristic;

/**
 *
 * @author Sally Al
 *
 */
public class PlayerHealthRule extends PlayerCondition {

	private RealHealthCharacteristic myPlayerHealth;

	public PlayerHealthRule(RealHealthCharacteristic player) {
		super(player);
		myPlayerHealth = player;
	}

	// Sally, this method has to be rewritten; the list should return something
	// for all players; not just for one; I added the requirements for the list
	// for getConditionResolution() method; let me know if you have any
	// questions
	public List<EndGameConditions> executeRule() {
		List<EndGameConditions> list = new ArrayList<EndGameConditions>();
		if (myPlayerHealth.getCurrentHealth() == 0) {
			list.add(EndGameConditions.LOSE);
		}
		return list;

	}

	@Override
	public List<EndGameConditions> getConditionResolution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasConditionResolved() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void evaluateEndResult() {
		// TODO Auto-generated method stub

	}

}
