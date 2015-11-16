package voogasalad_GucciGames.gameEngine.gameRules.defaultRules;

import java.util.List;
import java.util.Map;

import voogasalad_GucciGames.gameEngine.gameRules.Rules;
import voogasalad_GucciGames.gameEngine.mapObject.MapObjectType;

/**
 *
 * @author Sally Al
 *
 */
public class UnitsMovablePerTurn extends Rules {
	private int targetValue;
	private String target;

	public UnitsMovablePerTurn(MapObjectType theBigMap,  Map<String,Object> ruleArgs) {
		super(theBigMap,ruleArgs);
		targetValue = (int) ruleArgs.get(target);
	}

	@Override
	protected Boolean executeRules(List<Object> list) {

		int unitsMovedCounter = (int) list.get(0);
		if (unitsMovedCounter < targetValue) {
			return true;
		} else
			return false;
	}

}
